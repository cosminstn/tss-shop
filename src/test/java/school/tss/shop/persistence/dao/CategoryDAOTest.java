package school.tss.shop.persistence.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import school.tss.shop.BaseTest;
import school.tss.shop.persistence.entity.Category;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class CategoryDAOTest extends BaseTest {

    @Autowired
    private CategoryDAO categoryDAO;

    @Test
    public void createCategory() {

        Category category = new Category();

        category.setName("Random category");

        assertEquals("Random category", categoryDAO.create(category).getName());
    }

    @Test
    public void updateCategoryInDB() {

        Category category = new Category();

        category.setId(1);
        category.setName("Category1");


        int result = categoryDAO.update(category);

        assertEquals(1, result);

    }

    @Test
    public void updateCategoryNotInDB() {

        Category category = new Category();

        category.setId(5);
        category.setName("Category1");


        int result = categoryDAO.update(category);

        assertEquals(0, result);

    }

    @Test
    public void getCategoryByName() {

        assertEquals("Computers", categoryDAO.getByName("Computers").getName());
        assertEquals("Smartphones", categoryDAO.getByName("Smartphones").getName());


    }

    @AfterEach
    public void afterTestsCategory() {

        System.out.println("Test executed.");

    }

    @Test
    public void findAllCategories(){


        List<Category> allCategories = categoryDAO.findAll();
        assertEquals(3, allCategories.size());
        assertNotEquals(0, allCategories.size());


    }

    @Test
    public void deleteCategoryById(){

        assertEquals(1, categoryDAO.deleteById(1));
        assertEquals(0, categoryDAO.deleteById(8));

    }


}
