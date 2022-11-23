package leonardo.ezio.personal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-20 16:51
 */
@Document
public class Account {

    @Id
    private String id;

    private String accountCode;

    private String accountName;

    public Account() {
    }

    public Account(String accountCode, String accountName) {
        this.accountCode = accountCode;
        this.accountName = accountName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", accountCode='" + accountCode + '\'' +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}
