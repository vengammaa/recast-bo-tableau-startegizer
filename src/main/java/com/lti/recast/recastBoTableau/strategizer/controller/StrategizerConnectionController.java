package com.lti.recast.recastBoTableau.strategizer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.recast.recastBoTableau.strategizer.dto.ProjectReportTargetConModel;
import com.lti.recast.recastBoTableau.strategizer.dto.RptTargetConParamTypeModel;
import com.lti.recast.recastBoTableau.strategizer.services.StrategizerConnectionService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/strategizer/connections")
public class StrategizerConnectionController {
private static final Logger logger = LoggerFactory.getLogger(StrategizerConnectionController.class);
	
	@Autowired(required = false)
	private StrategizerConnectionService strategizerConnectionService;
	
	@GetMapping("/getConnections/{reportTypeCode}")
	public List<ProjectReportTargetConModel> getConnection(@PathVariable String reportTypeCode) {
		System.out.println("Get Connections API Called::");
		return strategizerConnectionService.getConnections(reportTypeCode);
	}
	
	@GetMapping("/params/{code}")
	public List<RptTargetConParamTypeModel> getParams(@PathVariable String code) {
		System.out.println("Inside get params::");
		return strategizerConnectionService.getParams(code);
	}
	
	
	@PostMapping("/addConnection")
	public void addConnection(@RequestBody ProjectReportTargetConModel p) {
		System.out.println("Inside add connection::");
		strategizerConnectionService.save(p);
	}
	
	@PutMapping("/editConnection")
	public void editConnection(@RequestBody ProjectReportTargetConModel pm) {
		System.out.println("Inside edit connection::");
		strategizerConnectionService.delete(pm.getId());
		strategizerConnectionService.save(pm);
	}
	
	@GetMapping("getConnectionDetails/{connectionId}")
	public ProjectReportTargetConModel getConnectionDetails(@PathVariable int connectionId) {
		System.out.println("Get connection details");
		return strategizerConnectionService.getConnectionDetails(connectionId);
	}
	
	@DeleteMapping("deleteConnection/{connectionId}")
	public String deleteConnection(@PathVariable int connectionId) {
		return strategizerConnectionService.delete(connectionId);
	}

}
