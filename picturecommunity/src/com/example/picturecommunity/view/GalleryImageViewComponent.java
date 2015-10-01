package com.example.picturecommunity.view;

import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import java.io.File;

import com.example.picturecommunity.controller.GalleryController;
import com.example.picturecommunity.model.Image;

// A container for a single image allowing user interaction with the underlying
// image collection of the specific user
// Parent: GalleryViewCOmponent
public class GalleryImageViewComponent extends CustomComponent{
	
	public GalleryImageViewComponent(Image img) {
		FileResource resource = new FileResource(new File(img.getPath()));
		com.vaadin.ui.Image embeddedImage = new com.vaadin.ui.Image(img.getName(), resource);

		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(embeddedImage);
		layout.addComponent(new Label(img.getComment()));
		HorizontalLayout uploadStatsAndViewStatus = new HorizontalLayout();
		//CheckBox viewStatus = new CheckBox("Private");
		CheckBox viewStatus = new CheckBox("Public", img.getViewStatus());
		viewStatus.addBlurListener(new BlurListener() {
			
			@Override
			public void blur(BlurEvent event) {
				if(viewStatus.getValue()) {
					Notification.show("Image is private");
				}
				else {
					Notification.show("Image is public");
				}
				
				//controller.changeViewStatus(viewStatus.getValue());
			}
		});
		Label uploader = new Label("Uploader: " + img.getUploader().getUserName());
		uploadStatsAndViewStatus.addComponent(uploader);
		uploadStatsAndViewStatus.setComponentAlignment(uploader, Alignment.MIDDLE_LEFT);
		uploadStatsAndViewStatus.addComponent(viewStatus);
		uploadStatsAndViewStatus.setComponentAlignment(viewStatus, Alignment.MIDDLE_RIGHT);
		//uploadStatsAndViewStatus.addComponent(new Label(img.getUploadTimeAsString()));
		//System.out.println("UPLOAD TIME STRING: " + img.getUploadTimeAsString());
		layout.addComponent(uploadStatsAndViewStatus);
		layout.setSizeUndefined();
		layout.setSpacing(true);
		setSizeUndefined();
		setCompositionRoot(layout);
	}
}