package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LearningServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html");

        URL urlFinalWeb = null;
        URL urlFinalImg = null;

        String string = ((req.getParameter("query").toString()).trim());
        string = string.replaceAll("\\s", "");

             resp.getWriter()
                    .print("<html><body bgcolor=white><form method=get action=/learning><center><div bgcolor=pink><font color=blue size=6>Enter any search keyword:</font><input type=text name=query><input type=submit name=converter value=Show Image!></div></center></form>");

        urlFinalWeb = getURLWeb(string);
        urlFinalImg = getURL(string);

        resp.getWriter().print(
                "<a href= " + urlFinalWeb + ">Know More</a><img src= "
                        + urlFinalImg + " height=250 width=250>");
    }

    public URL getURL(String queryImage) {
        URL url = null;
        try {
            {
                url = new URL(
                        "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q="
                                + queryImage.toLowerCase(Locale.ENGLISH));

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        url.openStream()));

                String s;
                String urlStr = null;
                while ((s = in.readLine()) != null) {

                    String[] st = s.split(",");

                    for (int i = 0; i < st.length; i++) {

                        {
                            String result = st[i];

                            if (result.contains("unescapedUrl")) {
                                String[] str = result.split(":");
                                StringBuilder strB = new StringBuilder();
                                strB.append(str[1]);
                                strB.append(":");
                                strB.append(str[2]);

                                strB.delete(0, 1);
                                strB.delete(strB.length() - 1, strB.length());

                                urlStr = strB.toString();
                                // urlList.add(urlStr);

                                if (urlStr.contains(queryImage)
                                        || urlStr.endsWith(queryImage)

                                        || urlStr.matches(queryImage)
                                        || urlStr.equalsIgnoreCase(queryImage)

                                        || urlStr.startsWith(queryImage)) {
                                    url = new URL(urlStr);
                                    break;
                                }

                                // Special treatment for applications/examples
                                else if (queryImage.contains("%20")) {
                                    String[] queryImg = queryImage.split("%20");

                                    if (urlStr.endsWith(queryImg[2])
                                            || urlStr.startsWith(queryImg[2])
                                            || urlStr.matches(queryImage)
                                            || urlStr
                                                    .equalsIgnoreCase(queryImage)
                                            || urlStr.contains(queryImg[2])) {
                                        url = new URL(urlStr);
                                        break;
                                    }
                                }
                            }
                        }
                    }

                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;

    }

    public URL getURLWeb(String queryImage) {
        URL url = null;
        try {
            {
                url = new URL(
                        "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&q="
                                + queryImage.toLowerCase(Locale.ENGLISH));

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        url.openStream()));

                String s;
                String urlStr = null;
                while ((s = in.readLine()) != null) {

                    String[] st = s.split(",");

                    for (int i = 0; i < st.length; i++) {

                        {
                            String result = st[i];

                            if (result.contains("unescapedUrl")) {
                                String[] str = result.split(":");
                                StringBuilder strB = new StringBuilder();
                                strB.append(str[1]);
                                strB.append(":");
                                strB.append(str[2]);

                                strB.delete(0, 1);
                                strB.delete(strB.length() - 1, strB.length());

                                urlStr = strB.toString();
                                // urlList.add(urlStr);

                                if (urlStr.contains(queryImage)
                                        || urlStr.endsWith(queryImage)

                                        || urlStr.matches(queryImage)
                                        || urlStr.equalsIgnoreCase(queryImage)

                                        || urlStr.startsWith(queryImage)) {
                                    url = new URL(urlStr);
                                    break;
                                }

                                // Special treatment for applications/examples
                                else if (queryImage.contains("%20")) {
                                    String[] queryImg = queryImage.split("%20");

                                    if (urlStr.endsWith(queryImg[2])
                                            || urlStr.startsWith(queryImg[2])
                                            || urlStr.matches(queryImage)
                                            || urlStr
                                                    .equalsIgnoreCase(queryImage)
                                            || urlStr.contains(queryImg[2])) {
                                        url = new URL(urlStr);
                                        break;
                                    }
                                }
                            }
                        }
                    }

                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}