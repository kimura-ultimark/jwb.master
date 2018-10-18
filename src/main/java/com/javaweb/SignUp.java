package com.javaweb;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.javaweb.common.user.ApplicationForm;
import com.javaweb.common.user.UserManager;
import com.javaweb.common.user.UserNotRegisteredException;
import com.javaweb.common.user.UserReception;
import com.javaweb.common.user.UserRegistrationForm;
import com.javaweb.markup.ModalPanel;
import com.javaweb.markup.ModalPanel.ModalSize;
import com.javaweb.markup.ModalPanel.PanelClosedCallback;

public class SignUp extends WebPage {
	
	private static final long serialVersionUID = 4612366711368313090L;
	
	public SignUp() {
		super();
		
		final ModalPanel modalPanel = new ModalPanel("feedbackPanel", ModalSize.SMALL);
		modalPanel.include(new FeedbackPanel(modalPanel.contentId()), "入力エラー");
		Form<ApplicationForm> signupForm = new Form<ApplicationForm>("signup-form", 
											new CompoundPropertyModel<ApplicationForm>(new UserRegistrationForm())) {

			private static final long serialVersionUID = -6555266186428851658L;
			
			@Override
			public void onSubmit() {
				UserReception userReception = new UserManager();
				try {
					userReception.SignUp(getModelObject());
					modalPanel.include(new FeedbackPanel(modalPanel.contentId()), "正常終了");
					info("ユーザー登録が正常に完了しました。");
					modalPanel.registerCallback(new PanelClosedCallback() {
						
						@Override
						public void onClose() {
							SignUp.this.setResponsePage(SignIn.class);
						}
					});
					modalPanel.show();
				} catch (UserNotRegisteredException e) {
					error(e.getLocalizedMessage());
					onError();
				}
			}
			
			@Override
			public void onError() {
				modalPanel.show();
			}
		};
		signupForm.add(new EmailTextField("emailAddress"), 
					new PasswordTextField("password"), 
					new PasswordTextField("passwordConfirm"));
		add(signupForm, modalPanel);
	}
}