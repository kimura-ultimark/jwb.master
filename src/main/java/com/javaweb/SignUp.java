package com.javaweb;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.javaweb.markup.ModalPanel;
import com.javaweb.markup.ModalPanel.ModalSize;

public class SignUp extends WebPage {
	
	private static final long serialVersionUID = 4612366711368313090L;
	
	private String email;
	
	private String password;
	
	private String passwordConfirm;
	
	public SignUp() {
		super();
		
		final ModalPanel modalPanel = new ModalPanel("feedbackPanel", ModalSize.SMALL);
		modalPanel.include(new FeedbackPanel(modalPanel.contentId()), "入力エラー");
		Form<SignUp> signupForm = new Form<>("signup-form", new CompoundPropertyModel<SignUp>(this)) {

			private static final long serialVersionUID = -6555266186428851658L;
			
			@Override
			public void onSubmit() {
				System.out.println(email);
				System.out.println(password);
				System.out.println(passwordConfirm);
			}
			
			@Override
			public void onError() {
				modalPanel.show();
			}
		};
		signupForm.add(new EmailTextField("email"), 
					new PasswordTextField("password"), 
					new PasswordTextField("passwordConfirm"));
		add(signupForm, modalPanel);
	}
}