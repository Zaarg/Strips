package org.no_ip.stieflo.services;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service 
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED) 
public @interface ReadOnlyTransactionalService { 
	
} 