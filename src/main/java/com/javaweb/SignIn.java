package com.javaweb;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import com.javaweb.common.user.IUser;
import com.javaweb.common.user.UserManager;
import com.javaweb.common.user.UserReception;
import com.javaweb.markup.ModalPanel;
import com.javaweb.markup.ModalPanel.ModalSize;

public class SignIn extends WebPage {

	private static final long serialVersionUID = -2113370665096840915L;
	
	public SignIn() {
		
		super();
		
		final EmailTextField emailField = new EmailTextField("email", new Model<String>());
		final PasswordTextField passwordField = new PasswordTextField("password", new Model<String>());
		final ModalPanel modalPanel = new ModalPanel("feedbackPanel", ModalSize.SMALL);
		modalPanel.include(new FeedbackPanel(modalPanel.contentId()), "入力エラー");
		
		Form<Void> signinForm = new Form<Void>("signin-form") {

			private static final long serialVersionUID = -5485239473312196050L;
			
			@Override
			public void onSubmit() {
				String emailAddress = emailField.getModelObject();
				String password = passwordField.getModelObject();
				UserReception userReception = new UserManager();
				IUser user = userReception.signIn(emailAddress, password);
				if (!user.isAuthenticated()) {
					error("サインインに失敗しました。メールアドレスかパスワードに誤りがあります。");
					onError();
				} else {
					setResponsePage(new TopPage());
				}
			}
			
			@Override
			public void onError() {
				modalPanel.show();
			}
		};
		signinForm.add(emailField, passwordField, new Link<Void>("signupLink") {

			private static final long serialVersionUID = 8112976778413910979L;

			@Override
			public void onClick() {
				setResponsePage(SignUp.class);
			}
		});
		add(signinForm, modalPanel);
	}
}