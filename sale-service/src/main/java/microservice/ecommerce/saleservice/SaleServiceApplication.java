package microservice.ecommerce.saleservice;

import microservice.ecommerce.commonpackage.utils.constants.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage, Paths.Sale.ServiceBasePackage})
public class SaleServiceApplication
{ public static void main(String[] args){ SpringApplication.run(SaleServiceApplication.class, args); } }