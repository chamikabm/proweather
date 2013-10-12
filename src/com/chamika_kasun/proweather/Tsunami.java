package com.chamika_kasun.proweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This Class is Used to Retrive and Show data of the Tsunami Information
 * @author Chamika
 * E-mail :  kasun.chamika@gmail.com
 */

public class Tsunami extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View contentView = inflater
				.inflate(R.layout.tsunami, container, false);
		return contentView;
	}

}
