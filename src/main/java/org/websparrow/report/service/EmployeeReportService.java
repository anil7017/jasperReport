package org.websparrow.report.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.websparrow.report.dto.Employee;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EmployeeReportService {

	private List<Employee> empList = Arrays.asList(
			new Employee(1, "Sandeep", "Data Matrix", "Front-end Developer", 20000),
			new Employee(2, "Prince", "Genpact", "Consultant", 40000),
			new Employee(3, "Gaurav", "Silver Touch ", "Sr. Java Engineer", 47000),
			new Employee(4, "Abhinav", "Akal Info Sys", "CTO", 700000));

	public String generateReport() {
		try {

			Resource resource = new FileSystemResource("");


			System.out.println(" resource.getURL() "+resource.getURL());
			System.out.println(resource.getURI());

			//String reportPath = "F:\\Content\\Report";
			String reportPath = "D:\\exaple project\\spring-boot-jasper-report-example\\springboot-jasper-report\\src\\main\\resources";

			// Compile the Jasper report from .jrxml to .japser
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\employee-rpt.jrxml");

			// Get your data source
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(empList);

			// Add parameters
			Map<String, Object> parameters = new HashMap<>();

			parameters.put("createdBy", "Websparrow.org");

			// Fill the report
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					jrBeanCollectionDataSource);

			// Export the report to a PDF file
			JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\Emp-Rpt.pdf");

			// export to byte array
			JasperExportManager.exportReportToPdf(jasperPrint);

			System.out.println("Done");

			return "Report successfully generated @path= " + reportPath;

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
