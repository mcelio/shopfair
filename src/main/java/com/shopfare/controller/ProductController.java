package com.shopfare.controller;

import com.shopfare.controller.mapper.ProductMapper;
import com.shopfare.datatransferobject.ProductDTO;
import com.shopfare.domainobject.ProductDO;
import com.shopfare.domainvalue.ProductType;
import com.shopfare.exception.ConstraintsViolationException;
import com.shopfare.exception.EntityNotFoundException;
import com.shopfare.search.ProductDOSpecificationBuilder;
import com.shopfare.service.driver.ProductService;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/products")
public class ProductController {

  private final ProductService productService;


  @Autowired
  public ProductController(final ProductService productService) {
    this.productService = productService;
  }


  @GetMapping("/{productId}")
  public ProductDTO getDriver(@Valid @PathVariable long productId) throws EntityNotFoundException {
    return ProductMapper.makeProductDTO(productService.find(productId));
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO)
      throws ConstraintsViolationException {
    ProductDO productDO = ProductMapper.makeProductDO(productDTO);
    return ProductMapper.makeProductDTO(productService.create(productDO));
  }


  @DeleteMapping("/{productId}")
  public void deleteDriver(@Valid @PathVariable long productId) throws EntityNotFoundException {
    productService.delete(productId);
  }
//
//  @PutMapping("/{productId}/car/{storeId}")
//  public void selectProduct(
//      @Valid @PathVariable long productId, @Valid @PathVariable long storeId)
//      throws EntityNotFoundException, CarAlreadyInUseException {
//    productService.selectProduct(productId, storeId);
//  }

//
//  @PutMapping("/{productId}/deselectproduct")
//  public void unselectCar(
//      @Valid @PathVariable long productId)
//      throws ConstraintsViolationException, EntityNotFoundException {
//    productService.unselectProduct(productId);
//  }


  @RequestMapping(method = RequestMethod.GET, value = "/search")
  @ResponseBody
  public List<ProductDO> search(@RequestParam(value = "q") String search,
      @RequestParam(value = "sorting") String sorting,
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "size") Integer size) {
    ProductDOSpecificationBuilder builder = new ProductDOSpecificationBuilder();
    Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
    Matcher matcher = pattern.matcher(search + ",");
    while (matcher.find()) {
      builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
    }
    Specification<ProductDO> specification = builder.build();
    return productService.search(specification, createPageRequest(page, size, sorting)).getContent();
  }


  /**
   * Pagination and sorting
   *
   * @return Pageable object
   */
  private Pageable createPageRequest(int page, int size, String sorting) {
    String fields[] = {"name", "productType", "rating"};
    Sort sort = new Sort(Sort.Direction.DESC, "store." + fields[0]);
    if (fields[1].equals(sorting)) {
      sort = new Sort(Sort.Direction.DESC, "store." + fields[1]);
    } else if (fields[2].equals(sorting)) {
      sort = new Sort(Sort.Direction.DESC, "store." + fields[2]);
    }
    return new PageRequest(page, size, sort);
  }


  @GetMapping
  public List<ProductDTO> findProducts(@RequestParam ProductType productType)
      throws ConstraintsViolationException, EntityNotFoundException {
    return ProductMapper.makeProductDTOList(productService.find(productType));
  }
}
