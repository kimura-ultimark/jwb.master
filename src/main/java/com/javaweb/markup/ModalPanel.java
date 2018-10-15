package com.javaweb.markup;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;


public class ModalPanel extends Panel {

	private static final long serialVersionUID = -4132594959168509154L;
	
	private PanelClosedCallback panelClosedCallback;
	
	private Label captionLabel;
	
	private WebMarkupContainer panel = new WebMarkupContainer("panel");
	
	public interface PanelClosedCallback {
		void onClose();
	}
	
	public enum ModalSize {
		SMALL("320px"),
		MEDIUM("640px"),
		LARGE("960px"),
		XLARGE("1024px");
		
		private String modalSize;
		
		private ModalSize(String modalSize) {
			this.modalSize = modalSize;
		}
		
		@Override
		public String toString() {
			return "width: " + modalSize + ";";
		}
	}

	public ModalPanel(String id) {
		this(id, ModalSize.MEDIUM);
	}
	
	public ModalPanel(String id, ModalSize modalSize) {
		super(id);
		captionLabel = new Label("panelTitle", Model.of(""));
		panel.add(new AttributeAppender("style", Model.of(modalSize.toString())));
		panel.add(captionLabel);
		panel.add(new WebMarkupContainer(contentId()));
		panel.add(new Link<Void>("closeButton") {

			private static final long serialVersionUID = -709565115123973124L;

			@Override
			public void onClick() {
				close();
			}
		});
		add(panel);
		setVisible(false);
	}
	
	public void include(Component component, String caption) {
		panel.remove(contentId());
		captionLabel.setDefaultModel(Model.of(caption));
		panel.add(component);
	}
	
	public String contentId() {
		return "panelContent";
	}
	
	public void registerCallback(PanelClosedCallback panelCloseCallback) {
		this.panelClosedCallback = panelCloseCallback;
	}
	
	public void show() {
		setVisible(true);
	}
	
	public void close() {
		if (panelClosedCallback != null) {
			panelClosedCallback.onClose();
		}
		setVisible(false);
	}
}