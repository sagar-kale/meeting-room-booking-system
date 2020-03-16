package com.sagar.meetingroom;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.sagar")
@EnableJpaRepositories("com.sagar.repository")
@EntityScan("com.sagar.entity")
public class MeetingRoomApplication {

	/*@Bean
	public FilterRegistrationBean remoteAddressFilter() {

		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		RemoteAddrFilter filter = new RemoteAddrFilter();

		filter.setAllow("127\\.\\d+\\.\\d+\\.\\d+|::1|0:0:0:0:0:0:0:1");

		filterRegistrationBean.setFilter(filter);
		filterRegistrationBean.addUrlPatterns("/api/*");

		return filterRegistrationBean;

	}*/

	public static void main(String[] args) {
		SpringApplication.run(MeetingRoomApplication.class, args);
	}
}
