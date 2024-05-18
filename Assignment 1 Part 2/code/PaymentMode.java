
// PaymentMode interface defines the contract for different payment modes to add money to a Wallet.
public interface PaymentMode {
    // Method to add money to a specified Wallet.
boolean addMoneyToWallet(Wallet w, float amount);
}

// InternetBanking class implements the PaymentMode interface and represents an internet banking payment mode.
class InternetBanking implements PaymentMode {
private String bankingID;

public InternetBanking(String bankingID) {
    this.bankingID = bankingID;
}

public boolean addMoneyToWallet(Wallet w, float amount) {
    w.addMoney(amount);
    return true;
}

public String getBankingID() {
    return this.bankingID;
}

public void setBankingID(String bankingID) {
    this.bankingID = bankingID;
}
}

// DebitCard class implements the PaymentMode interface and represents a debit card payment mode.
class DebitCard implements PaymentMode {
private String cardNo, cardName;
private int expireYear, expireMonth;

// Getter and setter methods for debit card details.

public String getCardNo() {
    return this.cardNo;
}

public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
}

public String getCardName() {
    return this.cardName;
}

public void setCardName(String cardName) {
    this.cardName = cardName;
}

public int getExpireYear() {
    return this.expireYear;
}

public void setExpireYear(int expireYear) {
    this.expireYear = expireYear;
}

public int getExpireMonth() {
    return this.expireMonth;
}

public void setExpireMonth(int expireMonth) {
    this.expireMonth = expireMonth;
}

public DebitCard(String cardNo, String cardName, int expireYear, int expireMonth) {
    this.cardNo = cardNo;
    this.cardName = cardName;
    this.expireYear = expireYear;
    this.expireMonth = expireMonth;
}
  // Implementation of the addMoneyToWallet method for debit card.
public boolean addMoneyToWallet(Wallet w, float amount) {
    w.addMoney(amount);
    return true;
}
}


// UPI class implements the PaymentMode interface and represents a UPI payment mode.
class UPI implements PaymentMode {
private String UPIid;

  // Constructor to initialize UPI with a UPI ID.
public String getUPIid() {
    return this.UPIid;
}

public void setUPIid(String uPIid) {
    UPIid = uPIid;
}

public UPI(String UPIid) {
    this.UPIid = UPIid;
}

public boolean addMoneyToWallet(Wallet w, float amount) {
    w.addMoney(amount);
    return true;
}
}
