package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    @Test
    public void testEquals_returnBothInstancesEqual() {
        Category category = setUpCategory();
        Category category1 = setUpCategory();
        assertEquals(category, category1);
    }

    @Test
    public void getName_savesNameSuccessfully() {
        Category category = setUpCategory();
        assertEquals("Thriller", category.getName());
    }

    @Test
    public void setName_changesNameSuccessfully() {
        Category category = setUpCategory();
        category.setName("Horror");
        assertEquals("Horror", category.getName());
    }

    @Test
    public void getDescription_savesDescriptionSuccessfully() {
        Category category = setUpCategory();
        assertEquals("In Manilla", category.getDescription());
    }

    @Test
    public void setDescription_changesDescriptionSuccessfully() {
        Category category = setUpCategory();
        category.setDescription("Fun");
        assertEquals("Fun", category.getDescription());
    }
    @Test
    public void setId_changesIdSuccessfully() {
        Category category = setUpCategory();
        category.setId(2);
        assertEquals(2, category.getId());
    }

    //helpers
    public Category setUpCategory(){
        return new Category("Thriller", "In Manilla");
    }
}