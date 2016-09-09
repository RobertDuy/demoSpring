package com.example.model;

public class RecipientFolder {
	private int folderId;
	private String folderName;
	private String fullName;
	
	public RecipientFolder(int folderId, String folderName, String fullName){
		this.folderId = folderId;
		this.folderName = folderName;
		this.fullName = fullName;
	}

	public int getFolderId() {
		return folderId;
	}

	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}
