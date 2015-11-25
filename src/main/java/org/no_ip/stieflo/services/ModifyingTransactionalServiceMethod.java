package org.no_ip.stieflo.services;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) 
@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED) 
public @interface ModifyingTransactionalServiceMethod { 
} 