package com.example.services.Interfaces;

import java.util.List;
import com.example.model.RecipientFolder;

public interface FolderInterface {
	public List<RecipientFolder> getListRecipientFolder(String sessionToken, boolean isIncludeRoot);
}
