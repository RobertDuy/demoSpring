package com.example.services.Interfaces;

import java.util.List;

import com.example.model.Recipient;

public interface WriterInterface<T extends Recipient> {
	public boolean writeRecipient(String sessionToken, List<T> recipients, String folderId);
}