package com.ourway.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

/**
 * Created by Administrator on 2017/7/5.
 */
public class ChosenboxViewModel {
    private ListModelList<String> contactsModel = new ListModelList<String>();
//    private ListModel<String> labelsModel = new ListModelList<String>(
//            EmailLabels.getLabels());
    private List<String> filesList = new ArrayList<String>();


    public List<String> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<String> filesList) {
        this.filesList = filesList;
    }

    @Init
    public void init() {

    }

    @Command("newContact")
    public void newContact(@BindingParam("contact") String contact) {
        contactsModel.add(contact);
        contactsModel.addToSelection(contact);
    }

    public ListModel<String> getContactsModel() {
        return contactsModel;
    }

    public ListModel<String> getLabelsModel() {
        return null;
    }

    @Command
    @NotifyChange("filesList")
    public void doUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {

        UploadEvent upEvent = null;
        Object objUploadEvent = ctx.getTriggerEvent();
        if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
            upEvent = (UploadEvent) objUploadEvent;
        }
        if (upEvent != null) {
            org.zkoss.util.media.Media[] medias = upEvent.getMedias();
            String pathToStore = getDestinationFolder();
            for (org.zkoss.util.media.Media m : medias) {
                filesList.add(m.getName());
                Files.copy(new File(pathToStore + m.getName()),
                        m.getStreamData());
            }

        }
    }

    private String getDestinationFolder() {

        String returnPath = null;
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH); // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        returnPath = Executions.getCurrent().getDesktop().getWebApp()
                .getRealPath("/");
        String yearPath = "\\" + "myFiles" + "\\" + year + "\\" + month + "\\"
                + day + "\\";
        returnPath = returnPath + yearPath;
        File baseDir = new File(returnPath);
        if (!baseDir.exists()) {
            baseDir.mkdirs();
        }
        return returnPath;
    }

    @Command
    @NotifyChange("filesList")
    public void onDelete(@BindingParam("currentFile") String curFile) {
        filesList.remove(curFile);
    }
}
