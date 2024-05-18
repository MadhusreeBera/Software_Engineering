package com.sismics.books.rest.resource;

import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

public interface IResource {
    public Response list() throws JSONException;
}
