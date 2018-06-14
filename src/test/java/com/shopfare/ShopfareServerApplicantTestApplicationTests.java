package com.shopfare;

import static org.assertj.core.api.Assertions.assertThat;

import com.shopfare.controller.StoreController;
import com.shopfare.controller.ProductController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShopfareServerApplicantTestApplication.class)
public class ShopfareServerApplicantTestApplicationTests {

  @Autowired
  private StoreController storeController;
  @Autowired
  private ProductController productController;

  @Test
  public void contextLoads() {
  }

  @Test
  public void storeControllerInitializedCorrectly() {
    assertThat(storeController).isNotNull();
  }


  @Test
  public void productControllerInitializedCorrectly() {
    assertThat(productController).isNotNull();
  }

}
