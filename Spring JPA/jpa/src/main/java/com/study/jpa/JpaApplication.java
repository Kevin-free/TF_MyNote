package com.study.jpa;

import com.study.jpa.entity.Customer;
import com.study.jpa.repository.BaseRepository;
import com.study.jpa.repository.CustomerByExampleRepository;
import com.study.jpa.repository.CustomerPageAndSortRepository;
import com.study.jpa.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@SpringBootApplication
public class JpaApplication {

	private static final Logger log = LoggerFactory
			.getLogger(JpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// 新增记录
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// 查找所有记录
			log.info("调用 findAll()方法：");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			long id = 6L;
			// 通过id查找一个记录
			Optional<Customer> customerOptional = repository.findById(id);
			if (customerOptional.isPresent()) {
				Customer customer = customerOptional.get();
				log.info("调用 findById()方法:");
				log.info("--------------------------------");
				log.info(customer.toString());
				log.info("");
			} else {
				log.error("id为" + id + "的记录不存在");
			}
			log.info("");

			// 通过last name查找一个记录
			log.info("调用 findByLastName('Bauer')方法:");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");

			// 查询指定记录是否存在
			long existId = 2L;
			log.info("调用 findByLastName('Bauer')方法:");
			log.info("--------------------------------------------");
			boolean existsById = repository.existsById(existId);
			if (existsById) {
				log.info("主键为" + existId + "的记录存在");
			} else {
				log.info("主键为" + existId + "的记录不存在");
			}
			log.info("");

			// 删除记录
			 long Palmer = repository.deleteByLastName("Palmer");
			 log.info("调用 deleteByLastName()方法后：");
			 log.info("删除的数量为："+Palmer);
//			long deleteId = 3L;
//			repository.deleteById(deleteId);
//			log.info("调用 deleteById()方法后：");
			log.info("-------------------------------");
			repository.findAll().forEach(customer -> {
				log.info(customer.toString());
			});
			log.info("");

			// 得到记录数量
			long count = repository.count();
			log.info("调用 count()方法后：");
			log.info("-------------------------------");
			log.info("总记录数为：" + count);
			log.info("");
		};
	}

	@Bean
	public CommandLineRunner pageDome(
			CustomerPageAndSortRepository repository) {
		return (args) -> {

			// 分页查找
			Page<Customer> customerPage = repository
					.findAll(PageRequest.of(0, 3));
			log.info("调用PagingAndSortingRepository的findAll()方法后：");
			log.info("-------------------------------");
			log.info(customerPage.getContent().toString());
			log.info("");

			// 排序
			Sort sort = Sort.by(Sort.Direction.DESC, "id");
			Iterable<Customer> all = repository.findAll(sort);
			all.forEach(customer -> {
				log.info(customer.toString());
			});
			log.info("");
		};
	}

	@Bean
	public CommandLineRunner exampleDemo(
			CustomerByExampleRepository repository) {
		return (args) -> {

			//example查找
			Customer customer = new Customer("Jack", "Bauer");
			Example<Customer> example = Example.of(customer);

			Optional<Customer> customerOptional = repository.findOne(example);
			if (customerOptional.isPresent()){
				log.info(customerOptional.get().toString());
			}else {
			    log.error("该记录不存在");
			}

		};
	}*/
}
