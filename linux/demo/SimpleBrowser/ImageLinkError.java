/*
 * Created on 2006-6-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import org.jdesktop.jdic.browser.BrowserEngineManager;
//import org.jdesktop.jdic.browser.IBrowserEngine;
import org.jdesktop.jdic.browser.WebBrowser;
import org.jdesktop.jdic.browser.WebBrowserEvent;
import org.jdesktop.jdic.browser.WebBrowserListener;

/**
 * @author syl
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ImageLinkError {
	public static void main(String[] args) {
		JFrame frame = new JFrame("JDIC API Demo - SimpleBrowser-ImageLinkError");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//set default using mozilla
		//without get default browser
		//				BrowserEngineManager.instance().setActiveEngine(
		//						BrowserEngineManager.IE);

		//for ie, you needn't set any path infos...

//		BrowserEngineManager bem= BrowserEngineManager.instance();
//        bem.setActiveEngine(BrowserEngineManager.MOZILLA);
//        IBrowserEngine be=bem.getActiveEngine();
//        //the path that constains xpcom.dll
//        be.setEnginePath("D:\\E_work\\work\\mozilla-1.4-source\\dist\\bin");
		
		final WebBrowser webBrowser = new WebBrowser();
		WebBrowser.setDebug(true);
		//Use below code to check the status of the navigation process,
		//or register a listener for the notification events.

		try {

			//			webBrowser.setURL(new URL("http://www.google.com"));
			webBrowser.setContent("<html>ask <img src=\"file:///home/jdic/backup/dist/linux/demo/SimpleBrowser/Waterlilies.jpg\"> </html>");
	//		webBrowser.setContent("<html>ask <img src=\"http://www.w3schools.com/images/ie.gif\"> </html>");
			//			webBrowser.setContent("<html>asfk <img
			// src=\"file:///C|/work/EclipseWorkspace/Jdic/mypic.jpg\">
			// </html>");

			// Below Chinese website tests unicode support.
			//webBrowser.setURL(new URL("http://www.google.com/intl/zh-CN/"));

			// Print out debug messages in the command line.
			//webBrowser.setDebug(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		webBrowser.addWebBrowserListener(new WebBrowserListener() {
			public void downloadStarted(WebBrowserEvent event) {
				;
			}

			public void downloadCompleted(WebBrowserEvent event) {
				;
			}

			public void downloadProgress(WebBrowserEvent event) {
				// updateStatusInfo("Loading in progress...");
				;
			}

			public void downloadError(WebBrowserEvent event) {
				;
			}

			public void titleChange(WebBrowserEvent event) {
				;
			}

			public void statusTextChange(WebBrowserEvent event) {
				;
				// updateStatusInfo("Status text changed.");
			}

			public void initializationCompleted(WebBrowserEvent event) {
				;
				// TODO Auto-generated method stub

			}

			public void documentCompleted(WebBrowserEvent event) {
				;
				// TODO Auto-generated method stub

			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(700, 500));
		panel.add(webBrowser, BorderLayout.CENTER);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}
