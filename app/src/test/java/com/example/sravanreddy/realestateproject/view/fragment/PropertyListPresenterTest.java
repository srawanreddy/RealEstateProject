package com.example.sravanreddy.realestateproject.view.fragment;

import com.example.sravanreddy.realestateproject.data.DataManager;
import com.example.sravanreddy.realestateproject.data.IDataSource;
import com.example.sravanreddy.realestateproject.models.PropertyModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @Package com.example.sravanreddy.realestateproject.view.fragment
 * @FileName PropertyListPresenterTest
 * @Date 5/13/18, 11:31 PM
 * @Author Created by fengchengding
 * @Description RealEstateProject
 */

public class PropertyListPresenterTest {


    @Mock
    private PropertyListContract.IView fragmentView;
    @Mock
    private DataManager mDataManager;
    @Mock
    private IDataSource.NetworkCallBack networkCallback;

    @Captor
    private ArgumentCaptor<IDataSource.NetworkCallBack> networkCallbackArgumentCaptor;

    private PresenterPropertyList projectFgtPresenter;

    private Object object = new Object();
    private ArrayList<PropertyModel> propertyModels = new ArrayList<>();

    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);

        //generate mock object
        fragmentView = mock(PropertyListContract.IView.class);
        mDataManager = mock(DataManager.class);

        projectFgtPresenter = new PresenterPropertyList(mDataManager, fragmentView);
    }


    @Test
    public void start() throws Exception {
        mDataManager.getProperties(networkCallback, "123");

        //check onFailure
        verify(mDataManager).getProperties(networkCallbackArgumentCaptor.capture(), "123");

        networkCallbackArgumentCaptor.getValue().onFailure(new Throwable());

        // Then a task is saved in the repository and the view updated
        verify(mDataManager).getProperties(networkCallbackArgumentCaptor.capture(), "123"); // saved to the model

        // Trigger callback so tasks are cached
        networkCallbackArgumentCaptor.getValue().onSuccess(object);

        verify(fragmentView).setRecylcerView(propertyModels); // shown in the UI
    }



}
