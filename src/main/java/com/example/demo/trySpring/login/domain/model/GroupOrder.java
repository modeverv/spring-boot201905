package com.example.demo.trySpring.login.domain.model;

import javax.validation.GroupSequence;

@GroupSequence({
		ValidGroup1.class,
		ValidGroup2.class,
		ValidGroup3.class
})
public interface GroupOrder {

}
