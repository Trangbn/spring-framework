package com.tgroup.trangbn.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.tgroup.trangbn.implementations", "com.tgroup.trangbn.services", "com.tgroup.trangbn.aspects"})
@EnableAspectJAutoProxy
public class ProjectConfiguration {
}
