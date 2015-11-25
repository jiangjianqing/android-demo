package my.android.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ztxs.myapplication2.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WebSiteContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WebSiteContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebSiteContentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private WebView webView;

    private OnFragmentInteractionListener mListener;

    private GestureDetector gestureDetector;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WebSiteContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebSiteContentFragment newInstance(String param1, String param2) {
        WebSiteContentFragment fragment = new WebSiteContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 刷新当前url
     * @param url
     */
    public void refreshFragment(String url){
        webView.loadUrl(url);
    }

    public WebSiteContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_web_site_content, container, false);
        webView=(WebView)view.findViewById(R.id.website_content_view);
        webView.getSettings().setJavaScriptEnabled(true);//启用javascript支持

        gestureDetector=new GestureDetector(getActivity(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                 && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    if(webView.canGoForward())
                        webView.goForward();
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                 && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    if(webView.canGoBack())
                        webView.goBack();
                }
                return false;
            }
        });

        gestureDetector.setIsLongpressEnabled(true);
        webView.setLongClickable(true);
        webView.setClickable(true);
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Toast.makeText((Context)getActivity(), "onTouch", Toast.LENGTH_SHORT).show();
                return gestureDetector.onTouchEvent(event);
            }
        });//监听触摸事件

        //缩放测试没有成功
        //webView.getSettings().setSupportZoom(true);//启用页面的缩放
        //webView.getSettings().setBuiltInZoomControls(true);//启用页面缩放的按钮

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //直接让本WebView加载url，不打开系统浏览器
                view.loadUrl(url);
                return true;

                //return super.shouldOverrideUrlLoading(view, url);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            //mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    //----------------------添加WebView控制

    public boolean canGoBack(){
        return webView.canGoBack();
    }

    public void goBack(){
        webView.goBack();
    }

}
