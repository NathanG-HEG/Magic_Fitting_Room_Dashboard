package edu.hh.dashboard.logic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/***
 * Utilities class to edit the index.html file in order adjust it and display new content
 */
public abstract class HtmlEditor {

    private static String htmlFile = Settings.getLocalRepository() + "/index.html";
    private static String folderName = "";

    /***
     * Rewrite the div element in the HTML file with the new content
     * @param divId the id of the div to rewrite
     * @param content The list of content to write
     * @throws IOException
     */
    private static void modifyDivElementsInHtmlFile(String divId, String[] content) throws IOException {
        File f = new File(htmlFile);

        Document doc = Jsoup.parse(f);
        Element mainDiv = doc.getElementById(divId);

        StringBuilder newDivContent = new StringBuilder();
        for (String s : content) {
            newDivContent.append(s);
        }

        mainDiv.text(newDivContent.toString());
        String newHtmlContent = doc.outerHtml().replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">");
        Files.writeString(f.toPath(), newHtmlContent);
    }

    /***
     * Modify the div element corresponding to a folder in the local repo
     * @param folder the folder that got modified (added or deleted pictures)
     */
    public static void modifyDivElementsFromFolder(String folder) {
        folderName = folder.replaceFirst(Settings.getLocalRepository() + "/clothes/", "") + "/";
        String divId;
        switch (folder.replaceFirst(Settings.getLocalRepository() + "/clothes/", "")) {
            case "Fire":
                divId = "fdiv";
                break;
            case "Metal":
                divId = "mdiv";
                break;
            case "Water":
                divId = "wdiv";
                break;
            case "Wood":
                divId = "wooddiv";
                break;
            default:
                divId = "ediv";
        }
        File[] files = new File(folder).listFiles();
        String[] newContent = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            newContent[i] = createDivElementFromFile(files[i].getName());
        }

        try {
            modifyDivElementsInHtmlFile(divId, newContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * Generate an element for the according to a file name
     * @param fileName The file name contained in the right clothes folder
     * @return a String containing the new HTML element.
     */
    private static String createDivElementFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        //        <ul class="imgList">
        //          <img src="clothes/Metal/alku_kult.png" id="alku-gold" onclick="addClothes(this.id)" />
        //        </ul>
        sb.append("<ul class=\"imgList\">")
                .append("\n\t")
                .append("<img src=\"")
                .append("clothes/")
                .append(folderName)
                .append(fileName)
                .append("\"")
                .append(" id=\"")
                .append(folderName, 0, 2)
                .append(fileName, 0, fileName.length() - 4)
                .append("\" onclick=\"addClothes(this.id)\" />")
                .append("\n")
                .append("</ul>")
                .append("\n");

        return sb.toString();

    }

}

