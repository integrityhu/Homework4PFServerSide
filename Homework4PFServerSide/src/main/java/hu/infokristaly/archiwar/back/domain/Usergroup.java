package hu.infokristaly.archiwar.back.domain;

// Generated 2012.09.26. 11:18:58 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usergroup generated by hbm2java
 */
@Entity
@Table(name = "usergroup", schema = "public")
public class Usergroup implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1631677817438656535L;
    private Long id;
    private String usergroup;
    private Boolean actdocumentincoming;
    private Boolean actdocumentregister;
    private Boolean actdocumentregisteredsearch;
    private Boolean actdocumentsending;
    private Boolean actdesktop;
    private Boolean actpostbox;
    private Boolean actdocumentincomingbooks;
    private Boolean actregisterbooks;
    private Boolean actdocumenttypes;
    private Boolean actdocumentstores;
    private Boolean actfilingplan;
    private Boolean actcustomers;
    private Boolean actcolleague;
    private Boolean actcompany;
    private Boolean actlogins;
    private Boolean acthelp;
    private Boolean actproperties;
    private Boolean actabout;
    private Boolean actorganizationunit;
    private Boolean acticons;
    private Boolean actdictionaries;
    private Boolean actusergroup;
    private Boolean actcseworkflowreplication;
    private Boolean actcseoutingdocuments;
    private boolean actregisterabledocuments;
    private Boolean actdispatchtimeout;
    private Boolean actoptions;
    private Boolean rgtsearchfilterbyclerk;
    private Boolean acterrorlog;

    public Usergroup() {
    }

    public Usergroup(Long id, boolean actregisterabledocuments) {
        this.id = id;
        this.actregisterabledocuments = actregisterabledocuments;
    }

    public Usergroup(Long id, String usergroup, Boolean actdocumentincoming, Boolean actdocumentregister, Boolean actdocumentregisteredsearch, Boolean actdocumentsending, Boolean actdesktop, Boolean actpostbox, Boolean actdocumentincomingbooks,
            Boolean actregisterbooks, Boolean actdocumenttypes, Boolean actdocumentstores, Boolean actfilingplan, Boolean actcustomers, Boolean actcolleague, Boolean actcompany, Boolean actlogins, Boolean acthelp, Boolean actproperties, Boolean actabout,
            Boolean actorganizationunit, Boolean acticons, Boolean actdictionaries, Boolean actusergroup, Boolean actcseworkflowreplication, Boolean actcseoutingdocuments, boolean actregisterabledocuments, Boolean actdispatchtimeout, Boolean actoptions,
            Boolean rgtsearchfilterbyclerk, Boolean acterrorlog) {
        this.id = id;
        this.usergroup = usergroup;
        this.actdocumentincoming = actdocumentincoming;
        this.actdocumentregister = actdocumentregister;
        this.actdocumentregisteredsearch = actdocumentregisteredsearch;
        this.actdocumentsending = actdocumentsending;
        this.actdesktop = actdesktop;
        this.actpostbox = actpostbox;
        this.actdocumentincomingbooks = actdocumentincomingbooks;
        this.actregisterbooks = actregisterbooks;
        this.actdocumenttypes = actdocumenttypes;
        this.actdocumentstores = actdocumentstores;
        this.actfilingplan = actfilingplan;
        this.actcustomers = actcustomers;
        this.actcolleague = actcolleague;
        this.actcompany = actcompany;
        this.actlogins = actlogins;
        this.acthelp = acthelp;
        this.actproperties = actproperties;
        this.actabout = actabout;
        this.actorganizationunit = actorganizationunit;
        this.acticons = acticons;
        this.actdictionaries = actdictionaries;
        this.actusergroup = actusergroup;
        this.actcseworkflowreplication = actcseworkflowreplication;
        this.actcseoutingdocuments = actcseoutingdocuments;
        this.actregisterabledocuments = actregisterabledocuments;
        this.actdispatchtimeout = actdispatchtimeout;
        this.actoptions = actoptions;
        this.rgtsearchfilterbyclerk = rgtsearchfilterbyclerk;
        this.acterrorlog = acterrorlog;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "usergroupid", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "usergroup", length = 50)
    public String getUsergroup() {
        return this.usergroup;
    }

    public void setUsergroup(String usergroup) {
        this.usergroup = usergroup;
    }

    @Column(name = "actdocumentincoming")
    public Boolean getActdocumentincoming() {
        return this.actdocumentincoming;
    }

    public void setActdocumentincoming(Boolean actdocumentincoming) {
        this.actdocumentincoming = actdocumentincoming;
    }

    @Column(name = "actdocumentregister")
    public Boolean getActdocumentregister() {
        return this.actdocumentregister;
    }

    public void setActdocumentregister(Boolean actdocumentregister) {
        this.actdocumentregister = actdocumentregister;
    }

    @Column(name = "actdocumentregisteredsearch")
    public Boolean getActdocumentregisteredsearch() {
        return this.actdocumentregisteredsearch;
    }

    public void setActdocumentregisteredsearch(Boolean actdocumentregisteredsearch) {
        this.actdocumentregisteredsearch = actdocumentregisteredsearch;
    }

    @Column(name = "actdocumentsending")
    public Boolean getActdocumentsending() {
        return this.actdocumentsending;
    }

    public void setActdocumentsending(Boolean actdocumentsending) {
        this.actdocumentsending = actdocumentsending;
    }

    @Column(name = "actdesktop")
    public Boolean getActdesktop() {
        return this.actdesktop;
    }

    public void setActdesktop(Boolean actdesktop) {
        this.actdesktop = actdesktop;
    }

    @Column(name = "actpostbox")
    public Boolean getActpostbox() {
        return this.actpostbox;
    }

    public void setActpostbox(Boolean actpostbox) {
        this.actpostbox = actpostbox;
    }

    @Column(name = "actdocumentincomingbooks")
    public Boolean getActdocumentincomingbooks() {
        return this.actdocumentincomingbooks;
    }

    public void setActdocumentincomingbooks(Boolean actdocumentincomingbooks) {
        this.actdocumentincomingbooks = actdocumentincomingbooks;
    }

    @Column(name = "actregisterbooks")
    public Boolean getActregisterbooks() {
        return this.actregisterbooks;
    }

    public void setActregisterbooks(Boolean actregisterbooks) {
        this.actregisterbooks = actregisterbooks;
    }

    @Column(name = "actdocumenttypes")
    public Boolean getActdocumenttypes() {
        return this.actdocumenttypes;
    }

    public void setActdocumenttypes(Boolean actdocumenttypes) {
        this.actdocumenttypes = actdocumenttypes;
    }

    @Column(name = "actdocumentstores")
    public Boolean getActdocumentstores() {
        return this.actdocumentstores;
    }

    public void setActdocumentstores(Boolean actdocumentstores) {
        this.actdocumentstores = actdocumentstores;
    }

    @Column(name = "actfilingplan")
    public Boolean getActfilingplan() {
        return this.actfilingplan;
    }

    public void setActfilingplan(Boolean actfilingplan) {
        this.actfilingplan = actfilingplan;
    }

    @Column(name = "actcustomers")
    public Boolean getActcustomers() {
        return this.actcustomers;
    }

    public void setActcustomers(Boolean actcustomers) {
        this.actcustomers = actcustomers;
    }

    @Column(name = "actcolleague")
    public Boolean getActcolleague() {
        return this.actcolleague;
    }

    public void setActcolleague(Boolean actcolleague) {
        this.actcolleague = actcolleague;
    }

    @Column(name = "actcompany")
    public Boolean getActcompany() {
        return this.actcompany;
    }

    public void setActcompany(Boolean actcompany) {
        this.actcompany = actcompany;
    }

    @Column(name = "actlogins")
    public Boolean getActlogins() {
        return this.actlogins;
    }

    public void setActlogins(Boolean actlogins) {
        this.actlogins = actlogins;
    }

    @Column(name = "acthelp")
    public Boolean getActhelp() {
        return this.acthelp;
    }

    public void setActhelp(Boolean acthelp) {
        this.acthelp = acthelp;
    }

    @Column(name = "actproperties")
    public Boolean getActproperties() {
        return this.actproperties;
    }

    public void setActproperties(Boolean actproperties) {
        this.actproperties = actproperties;
    }

    @Column(name = "actabout")
    public Boolean getActabout() {
        return this.actabout;
    }

    public void setActabout(Boolean actabout) {
        this.actabout = actabout;
    }

    @Column(name = "actorganizationunit")
    public Boolean getActorganizationunit() {
        return this.actorganizationunit;
    }

    public void setActorganizationunit(Boolean actorganizationunit) {
        this.actorganizationunit = actorganizationunit;
    }

    @Column(name = "acticons")
    public Boolean getActicons() {
        return this.acticons;
    }

    public void setActicons(Boolean acticons) {
        this.acticons = acticons;
    }

    @Column(name = "actdictionaries")
    public Boolean getActdictionaries() {
        return this.actdictionaries;
    }

    public void setActdictionaries(Boolean actdictionaries) {
        this.actdictionaries = actdictionaries;
    }

    @Column(name = "actusergroup")
    public Boolean getActusergroup() {
        return this.actusergroup;
    }

    public void setActusergroup(Boolean actusergroup) {
        this.actusergroup = actusergroup;
    }

    @Column(name = "actcseworkflowreplication")
    public Boolean getActcseworkflowreplication() {
        return this.actcseworkflowreplication;
    }

    public void setActcseworkflowreplication(Boolean actcseworkflowreplication) {
        this.actcseworkflowreplication = actcseworkflowreplication;
    }

    @Column(name = "actcseoutingdocuments")
    public Boolean getActcseoutingdocuments() {
        return this.actcseoutingdocuments;
    }

    public void setActcseoutingdocuments(Boolean actcseoutingdocuments) {
        this.actcseoutingdocuments = actcseoutingdocuments;
    }

    @Column(name = "actregisterabledocuments", nullable = false)
    public boolean isActregisterabledocuments() {
        return this.actregisterabledocuments;
    }

    public void setActregisterabledocuments(boolean actregisterabledocuments) {
        this.actregisterabledocuments = actregisterabledocuments;
    }

    @Column(name = "actdispatchtimeout")
    public Boolean getActdispatchtimeout() {
        return this.actdispatchtimeout;
    }

    public void setActdispatchtimeout(Boolean actdispatchtimeout) {
        this.actdispatchtimeout = actdispatchtimeout;
    }

    @Column(name = "actoptions")
    public Boolean getActoptions() {
        return this.actoptions;
    }

    public void setActoptions(Boolean actoptions) {
        this.actoptions = actoptions;
    }

    @Column(name = "rgtsearchfilterbyclerk")
    public Boolean getRgtsearchfilterbyclerk() {
        return this.rgtsearchfilterbyclerk;
    }

    public void setRgtsearchfilterbyclerk(Boolean rgtsearchfilterbyclerk) {
        this.rgtsearchfilterbyclerk = rgtsearchfilterbyclerk;
    }

    @Column(name = "acterrorlog")
    public Boolean getActerrorlog() {
        return this.acterrorlog;
    }

    public void setActerrorlog(Boolean acterrorlog) {
        this.acterrorlog = acterrorlog;
    }

}