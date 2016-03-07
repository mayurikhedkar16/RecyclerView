package info.androidhive.recyclerview.ContentLibrary;

/**
 * Created by Gb on 15/01/16.
 */
public class ContentLibThumbnail_POJO {


    String Category;
    String Category_NAME;
    String Category_TITLE;
    String Cat_SEQ;
    String Cat_IMAG_Path;
    String Cat_CONTENT_Path;
    String CAT_CONTENT_TAG;
    String CAT_CONTENT_DESCRIPTION;
    String CAT_CONTENT_SEQ;

    public ContentLibThumbnail_POJO(String Category,
                                    String Category_NAME,
                                    String Category_TITLE,
                                    String Cat_SEQ,
                                    String Cat_IMAG_Path,
                                    String Cat_CONTENT_Path,
                                    String CAT_CONTENT_TAG,
                                    String CAT_CONTENT_DESCRIPTION,
                                    String CAT_CONTENT_SEQ) {

        this.Category = Category;
        this.Category_NAME = Category_NAME;
        this.Category_TITLE = Category_TITLE;
        this.Cat_SEQ = Cat_SEQ;
        this.Cat_IMAG_Path = Cat_IMAG_Path;
        this.Cat_CONTENT_Path = Cat_CONTENT_Path;
        this.CAT_CONTENT_TAG = CAT_CONTENT_TAG;
        this.CAT_CONTENT_DESCRIPTION = CAT_CONTENT_DESCRIPTION;
        this.CAT_CONTENT_SEQ = CAT_CONTENT_SEQ;


    }


    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getCategory_NAME() {
        return Category_NAME;
    }

    public void setCategory_NAME(String category_NAME) {
        Category_NAME = category_NAME;
    }

    public String getCategory_TITLE() {
        return Category_TITLE;
    }

    public void setCategory_TITLE(String category_TITLE) {
        Category_TITLE = category_TITLE;
    }

    public String getCat_SEQ() {
        return Cat_SEQ;
    }

    public void setCat_SEQ(String cat_SEQ) {
        Cat_SEQ = cat_SEQ;
    }

    public String getCat_IMAG_Path() {
        return Cat_IMAG_Path;
    }

    public void setCat_IMAG_Path(String cat_IMAG_Path) {
        Cat_IMAG_Path = cat_IMAG_Path;
    }

    public String getCat_CONTENT_Path() {
        return Cat_CONTENT_Path;
    }

    public void setCat_CONTENT_Path(String cat_CONTENT_Path) {
        Cat_CONTENT_Path = cat_CONTENT_Path;
    }

    public String getCAT_CONTENT_TAG() {
        return CAT_CONTENT_TAG;
    }

    public void setCAT_CONTENT_TAG(String CAT_CONTENT_TAG) {
        this.CAT_CONTENT_TAG = CAT_CONTENT_TAG;
    }

    public String getCAT_CONTENT_DESCRIPTION() {
        return CAT_CONTENT_DESCRIPTION;
    }

    public void setCAT_CONTENT_DESCRIPTION(String CAT_CONTENT_DESCRIPTION) {
        this.CAT_CONTENT_DESCRIPTION = CAT_CONTENT_DESCRIPTION;
    }

    public String getCAT_CONTENT_SEQ() {
        return CAT_CONTENT_SEQ;
    }

    public void setCAT_CONTENT_SEQ(String CAT_CONTENT_SEQ) {
        this.CAT_CONTENT_SEQ = CAT_CONTENT_SEQ;
    }


}
