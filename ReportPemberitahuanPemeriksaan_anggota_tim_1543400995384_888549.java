/*
 * Generated by JasperReports - 11/28/18 5:29 PM
 */
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.*;

import java.util.*;
import java.math.*;
import java.text.*;
import java.io.*;
import java.net.*;

import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.model.Pegawai;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 *
 */
public class ReportPemberitahuanPemeriksaan_anggota_tim_1543400995384_888549 extends JREvaluator
{


    /**
     *
     */
    private JRFillParameter parameter_REPORT_CONNECTION = null;
    private JRFillParameter parameter_FILTER = null;
    private JRFillParameter parameter_JASPER_REPORT = null;
    private JRFillParameter parameter_REPORT_LOCALE = null;
    private JRFillParameter parameter_REPORT_TIME_ZONE = null;
    private JRFillParameter parameter_REPORT_TEMPLATES = null;
    private JRFillParameter parameter_REPORT_MAX_COUNT = null;
    private JRFillParameter parameter_REPORT_SCRIPTLET = null;
    private JRFillParameter parameter_JASPER_REPORTS_CONTEXT = null;
    private JRFillParameter parameter_REPORT_FORMAT_FACTORY = null;
    private JRFillParameter parameter_REPORT_PARAMETERS_MAP = null;
    private JRFillParameter parameter_REPORT_RESOURCE_BUNDLE = null;
    private JRFillParameter parameter_REPORT_DATA_SOURCE = null;
    private JRFillParameter parameter_REPORT_CONTEXT = null;
    private JRFillParameter parameter_REPORT_CLASS_LOADER = null;
    private JRFillParameter parameter_SORT_FIELDS = null;
    private JRFillField field_nipPegawai = null;
    private JRFillField field_golongan = null;
    private JRFillField field_namaPegawai = null;
    private JRFillField field_jabatanTim = null;
    private JRFillVariable variable_PAGE_NUMBER = null;
    private JRFillVariable variable_MASTER_CURRENT_PAGE = null;
    private JRFillVariable variable_MASTER_TOTAL_PAGES = null;
    private JRFillVariable variable_COLUMN_NUMBER = null;
    private JRFillVariable variable_REPORT_COUNT = null;
    private JRFillVariable variable_PAGE_COUNT = null;
    private JRFillVariable variable_COLUMN_COUNT = null;


    /**
     *
     */
    public void customizedInit(
        Map pm,
        Map fm,
        Map vm
        )
    {
        initParams(pm);
        initFields(fm);
        initVars(vm);
    }


    /**
     *
     */
    private void initParams(Map pm)
    {
        parameter_REPORT_CONNECTION = (JRFillParameter)pm.get("REPORT_CONNECTION");
        parameter_FILTER = (JRFillParameter)pm.get("FILTER");
        parameter_JASPER_REPORT = (JRFillParameter)pm.get("JASPER_REPORT");
        parameter_REPORT_LOCALE = (JRFillParameter)pm.get("REPORT_LOCALE");
        parameter_REPORT_TIME_ZONE = (JRFillParameter)pm.get("REPORT_TIME_ZONE");
        parameter_REPORT_TEMPLATES = (JRFillParameter)pm.get("REPORT_TEMPLATES");
        parameter_REPORT_MAX_COUNT = (JRFillParameter)pm.get("REPORT_MAX_COUNT");
        parameter_REPORT_SCRIPTLET = (JRFillParameter)pm.get("REPORT_SCRIPTLET");
        parameter_JASPER_REPORTS_CONTEXT = (JRFillParameter)pm.get("JASPER_REPORTS_CONTEXT");
        parameter_REPORT_FORMAT_FACTORY = (JRFillParameter)pm.get("REPORT_FORMAT_FACTORY");
        parameter_REPORT_PARAMETERS_MAP = (JRFillParameter)pm.get("REPORT_PARAMETERS_MAP");
        parameter_REPORT_RESOURCE_BUNDLE = (JRFillParameter)pm.get("REPORT_RESOURCE_BUNDLE");
        parameter_REPORT_DATA_SOURCE = (JRFillParameter)pm.get("REPORT_DATA_SOURCE");
        parameter_REPORT_CONTEXT = (JRFillParameter)pm.get("REPORT_CONTEXT");
        parameter_REPORT_CLASS_LOADER = (JRFillParameter)pm.get("REPORT_CLASS_LOADER");
        parameter_SORT_FIELDS = (JRFillParameter)pm.get("SORT_FIELDS");
    }


    /**
     *
     */
    private void initFields(Map fm)
    {
        field_nipPegawai = (JRFillField)fm.get("nipPegawai");
        field_golongan = (JRFillField)fm.get("golongan");
        field_namaPegawai = (JRFillField)fm.get("namaPegawai");
        field_jabatanTim = (JRFillField)fm.get("jabatanTim");
    }


    /**
     *
     */
    private void initVars(Map vm)
    {
        variable_PAGE_NUMBER = (JRFillVariable)vm.get("PAGE_NUMBER");
        variable_MASTER_CURRENT_PAGE = (JRFillVariable)vm.get("MASTER_CURRENT_PAGE");
        variable_MASTER_TOTAL_PAGES = (JRFillVariable)vm.get("MASTER_TOTAL_PAGES");
        variable_COLUMN_NUMBER = (JRFillVariable)vm.get("COLUMN_NUMBER");
        variable_REPORT_COUNT = (JRFillVariable)vm.get("REPORT_COUNT");
        variable_PAGE_COUNT = (JRFillVariable)vm.get("PAGE_COUNT");
        variable_COLUMN_COUNT = (JRFillVariable)vm.get("COLUMN_COUNT");
    }


    /**
     *
     */
    public Object evaluate(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=7$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateOld(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=7$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateEstimated(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=7$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


}
