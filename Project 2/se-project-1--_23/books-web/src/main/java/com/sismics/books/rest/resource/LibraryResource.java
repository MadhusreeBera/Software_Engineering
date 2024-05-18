package com.sismics.books.rest.resource;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.sismics.books.core.dao.jpa.TagDao;
import com.sismics.books.core.dao.jpa.UserBookDao;
import com.sismics.books.core.dao.jpa.UserBookRatingDao;
import com.sismics.books.core.dao.jpa.criteria.UserBookCriteria;
import com.sismics.books.core.dao.jpa.dto.TagDto;
import com.sismics.books.core.dao.jpa.dto.UserBookDto;
import com.sismics.books.core.model.jpa.*;
import com.sismics.books.core.util.jpa.PaginatedList;
import com.sismics.books.core.util.jpa.PaginatedLists;
import com.sismics.books.core.util.jpa.SortCriteria;
import com.sismics.rest.exception.ForbiddenClientException;
import com.sismics.rest.exception.ServerException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/library")
public class LibraryResource extends BaseResource{

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(
            @QueryParam("limit") Integer limit,
            @QueryParam("offset") Integer offset,
            @QueryParam("sort_column") Integer sortColumn,
            @QueryParam("asc") Boolean asc,
            @QueryParam("tag") String tagName
            ) throws JSONException {
        if (!authenticate()) {
            throw new ForbiddenClientException();
        }

        JSONObject response = new JSONObject();
        List<JSONObject> books = new ArrayList<>();

        UserBookDao userBookDao = new UserBookDao();
        TagDao tagDao = new TagDao();
        PaginatedList<UserBookDto> paginatedList = PaginatedLists.create(limit, offset);
        SortCriteria sortCriteria = new SortCriteria(sortColumn, asc);
        if (!Strings.isNullOrEmpty(tagName)) {
            Tag tag = tagDao.getByName(principal.getId(), tagName);
        }
        Library library;
        try {
            userBookDao.findAll(paginatedList, sortCriteria);
            library = Library.getInstance();
            library.setBooksList(paginatedList.getResultList());
        } catch (Exception e) {
            throw new ServerException("SearchError", "Error searching in books", e);
        }

        for (UserBookDto userBookDto : library.getBooksList()) {
            JSONObject book = new JSONObject();
            book.put("id", userBookDto.getId());
            book.put("title", userBookDto.getTitle());
            book.put("subtitle", userBookDto.getSubtitle());
            book.put("author", userBookDto.getAuthor());
            book.put("genres", userBookDto.getGenres());
            book.put("language", userBookDto.getLanguage());
            book.put("publish_date", userBookDto.getPublishTimestamp());
            book.put("create_date", userBookDto.getCreateTimestamp());
            book.put("read_date", userBookDto.getReadTimestamp());

            // Get tags
            List<TagDto> tagDtoList = tagDao.getByUserBookId(userBookDto.getId());
            List<JSONObject> tags = new ArrayList<>();
            for (TagDto tagDto : tagDtoList) {
                JSONObject tag = new JSONObject();
                tag.put("id", tagDto.getId());
                tag.put("name", tagDto.getName());
                tag.put("color", tagDto.getColor());
                tags.add(tag);
            }
            book.put("tags", tags);
            book.put("avg_rating",
                    UserBookRatingDao.getAvgRatingByID(userBookDto.getId()));
            book.put("num_ratings",
                    UserBookRatingDao.getNumRatingsByID(userBookDto.getId()));


            books.add(book);
        }
        response.put("total", paginatedList.getResultCount());
        response.put("books", books);

        return Response.ok().entity(response).build();
    }

    @GET
    @Path("top10")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTop10(
            @QueryParam("sort") String selectedCriteria
    ) throws JSONException {
        if (!authenticate()) {
            throw new ForbiddenClientException();
        }
        Integer limit=100, offset=0, sortColumn=0;
        Boolean asc=false;
        System.out.println("Helooooo");
        JSONObject response = new JSONObject();
        List<JSONObject> books = new ArrayList<>();

        UserBookDao userBookDao = new UserBookDao();
        PaginatedList<UserBookDto> paginatedList = PaginatedLists.create(limit, offset);
        SortCriteria sortCriteria = new SortCriteria(sortColumn, asc);
        Library library;
        try {
            userBookDao.findAll(paginatedList, sortCriteria);
            library = Library.getInstance();
            library.setBooksList(paginatedList.getResultList());
        } catch (Exception e) {
            throw new ServerException("SearchError", "Error searching in books", e);
        }

        library = Library.getInstance();
        List<UserBookDto> booksList;
        try {
            booksList = library.getBooksList();
        } catch (Exception e) {
            throw new ServerException("Top10Error", "Error getting top 10 books", e);
        }

        // sort logic
        SortingStrategy sortingStrategy;
        if (selectedCriteria.equals("avg_rating")) {
            sortingStrategy = new AverageRatingSortingStrategy();
        }
        else {
            sortingStrategy = new NumberOfRatingsSortingStrategy();
        }
        Top10List top10List = new Top10List();

        List<UserBookDto> top10books = top10List.getTop10Books(booksList, sortingStrategy);

        for (UserBookDto userBookDto : top10books) {
            JSONObject book = new JSONObject();
            book.put("id", userBookDto.getId());
            book.put("title", userBookDto.getTitle());
            book.put("subtitle", userBookDto.getSubtitle());
            book.put("author", userBookDto.getAuthor());
            book.put("genres", userBookDto.getGenres());
            book.put("language", userBookDto.getLanguage());
            book.put("publish_date", userBookDto.getPublishTimestamp());
            book.put("create_date", userBookDto.getCreateTimestamp());
            book.put("read_date", userBookDto.getReadTimestamp());

            // Get tags
            TagDao tagDao = new TagDao();
            List<TagDto> tagDtoList = tagDao.getByUserBookId(userBookDto.getId());
            List<JSONObject> tags = new ArrayList<>();
            for (TagDto tagDto : tagDtoList) {
                JSONObject tag = new JSONObject();
                tag.put("id", tagDto.getId());
                tag.put("name", tagDto.getName());
                tag.put("color", tagDto.getColor());
                tags.add(tag);
            }
            book.put("tags", tags);
            book.put("avg_rating",
                    UserBookRatingDao.getAvgRatingByID(userBookDto.getId()));
            book.put("num_ratings",
                    UserBookRatingDao.getNumRatingsByID(userBookDto.getId()));
            books.add(book);
        }
        response.put("total", top10books.size());
        response.put("books", books);
        System.out.println(response);
        System.out.println(Response.ok().entity(response).build());
        return Response.ok().entity(response).build();
    }

    @GET
    @Path("filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFilter(
            @QueryParam("selectedCriteria") Integer selectedCriteria,
            @QueryParam("listCriteria") String listCriteria
    ) throws JSONException {
        if (!authenticate()) {
            throw new ForbiddenClientException();
        }
        Integer limit=100, offset=0, sortColumn=0;
        Boolean asc=false;
        System.out.println("List Filter called");
        JSONObject response = new JSONObject();
        List<JSONObject> books = new ArrayList<>();

        UserBookDao userBookDao = new UserBookDao();
        PaginatedList<UserBookDto> paginatedList = PaginatedLists.create(limit, offset);
        SortCriteria sortCriteria = new SortCriteria(sortColumn, asc);
        Library library;
        try {
            userBookDao.findAll(paginatedList, sortCriteria);
            library = Library.getInstance();
            library.setBooksList(paginatedList.getResultList());
        } catch (Exception e) {
            throw new ServerException("SearchError", "Error searching in books", e);
        }
        System.out.println("Books" + library.getBooksList().size());
        library = Library.getInstance();
        List<UserBookDto> booksList;
        try {
            booksList = library.getBooksList();
        } catch (Exception e) {
            throw new ServerException("Top10Error", "Error getting top 10 books", e);
        }

        // filter logic
        String[] items = listCriteria.split(",");
        List<String> itemList = Arrays.asList(items);
        System.out.println("Items:" + items);
        FilterCriteria filterCriteria;
        if(selectedCriteria == 1){
            System.out.println("author filter");
            filterCriteria = new AuthorFilterCriteria(itemList.get(0));
        } else if (selectedCriteria == 2) {
            System.out.println("genre filter");
            System.out.println(itemList);
            filterCriteria = new GenreFilterCriteria(itemList);
        }
        else{filterCriteria = new RatingFilterCriteria(Integer.parseInt(listCriteria));}

        List<UserBookDto> filteredBooks = new ArrayList<>();
        for (UserBookDto book: booksList) {
            if (filterCriteria.meetsCriteria(book)) {
                filteredBooks.add(book);
            }
        }


        for (UserBookDto userBookDto : filteredBooks) {
            JSONObject book = new JSONObject();
            book.put("id", userBookDto.getId());
            book.put("title", userBookDto.getTitle());
            book.put("subtitle", userBookDto.getSubtitle());
            book.put("author", userBookDto.getAuthor());
            book.put("genres", userBookDto.getGenres());
            book.put("language", userBookDto.getLanguage());
            book.put("publish_date", userBookDto.getPublishTimestamp());
            book.put("create_date", userBookDto.getCreateTimestamp());
            book.put("read_date", userBookDto.getReadTimestamp());

            // Get tags
            TagDao tagDao = new TagDao();
            List<TagDto> tagDtoList = tagDao.getByUserBookId(userBookDto.getId());
            List<JSONObject> tags = new ArrayList<>();
            for (TagDto tagDto : tagDtoList) {
                JSONObject tag = new JSONObject();
                tag.put("id", tagDto.getId());
                tag.put("name", tagDto.getName());
                tag.put("color", tagDto.getColor());
                tags.add(tag);
            }
            book.put("tags", tags);
            book.put("avg_rating",
                    UserBookRatingDao.getAvgRatingByID(userBookDto.getId()));
            book.put("num_ratings",
                    UserBookRatingDao.getNumRatingsByID(userBookDto.getId()));
            books.add(book);
        }
        response.put("total", filteredBooks.size());
        response.put("books", books);
        System.out.println(response);
        System.out.println(Response.ok().entity(response).build());
        return Response.ok().entity(response).build();
    }
}
