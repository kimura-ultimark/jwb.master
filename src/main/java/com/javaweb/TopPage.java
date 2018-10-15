package com.javaweb;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public class TopPage extends WebPage {

	private static final long serialVersionUID = -3717495948013541197L;
	
	public TopPage() {
		add(new Label("message", Model.of("サインイン成功")));
	}
}