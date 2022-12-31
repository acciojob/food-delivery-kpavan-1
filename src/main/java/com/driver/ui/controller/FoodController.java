package com.driver.ui.controller;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodServiceImpl foodService;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto foodDto=foodService.getFoodById(id);
		return FoodDetailsResponse.builder().foodId(foodDto.getFoodId()).foodCategory(foodDto.getFoodCategory()).foodName(foodDto.getFoodName()).foodPrice(foodDto.getFoodPrice()).build();
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		FoodDto foodDto=foodService.createFood(FoodDto.builder().foodCategory(foodDetails.getFoodCategory()).foodName(foodDetails.getFoodName()).foodPrice(foodDetails.getFoodPrice()).build());
		return FoodDetailsResponse.builder().foodId(foodDto.getFoodId()).foodCategory(foodDto.getFoodCategory()).foodName(foodDto.getFoodName()).foodPrice(foodDto.getFoodPrice()).build();
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto=foodService.updateFoodDetails(id,FoodDto.builder().foodCategory(foodDetails.getFoodCategory()).foodName(foodDetails.getFoodName()).foodPrice(foodDetails.getFoodPrice()).build());
		return FoodDetailsResponse.builder().foodId(foodDto.getFoodId()).foodCategory(foodDto.getFoodCategory()).foodName(foodDto.getFoodName()).foodPrice(foodDto.getFoodPrice()).build();
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		foodService.deleteFoodItem(id);
		OperationStatusModel operationStatusModel=OperationStatusModel.builder().operationResult(String.valueOf(RequestOperationStatus.SUCCESS)).operationName(String.valueOf(RequestOperationName.DELETE)).build();
		return operationStatusModel;
	}

	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDetailsResponse> foodDetailsResponses = null;
		for(FoodDto foodDto: foodService.getFoods()){
			foodDetailsResponses.add(FoodDetailsResponse.builder().foodName(foodDto.getFoodName()).foodPrice(foodDto.getFoodPrice()).foodId(foodDto.getFoodId()).foodCategory(foodDto.getFoodCategory()).build());
		}
		return foodDetailsResponses;
	}
}