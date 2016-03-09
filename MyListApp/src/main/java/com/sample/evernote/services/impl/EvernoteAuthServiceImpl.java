package com.sample.evernote.services.impl;

import org.springframework.stereotype.Service;

import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.sample.evermote.services.EvernoteAuthService;

@Service
public class EvernoteAuthServiceImpl implements EvernoteAuthService{

	@Override
	public EvernoteAuth getEvernoteAuthToken() {
		String developerToken = "S=s1:U=922b6:E=15a7bf0abe5:C=153243f7d70:P=1cd:A=en-devtoken:V=2:H=839ea2b4751d10aa2438b384726b6e32";
		EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.SANDBOX, developerToken);
		return evernoteAuth;
	}

}
