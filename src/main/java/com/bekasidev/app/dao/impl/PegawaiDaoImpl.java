package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.PegawaiDao;
import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.Tim;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PegawaiDaoImpl implements PegawaiDao {
    @Override
    public List<Pegawai> getAllPegawai() {
        String sql = "SELECT * FROM pegawai";
        List<Pegawai> listPegawai = new ArrayList<>();

        try(Connection conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)) {
            while(rs.next()){
                listPegawai.add(setPegawai(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listPegawai;
    }
    
    private Pegawai getPegawai(String nip) {
        String sql = "SELECT * FROM pegawai WHERE nip_pegawai=?";
        Pegawai pegawai = null;
        
        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nip);
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                pegawai = setPegawai(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pegawai;
    }
    
//    @Override
    public List<Pegawai> getPegawaiByTimBack(String idTim) {
        String sql = "SELECT * FROM pegawai WHERE id_tim=?";
        List<Pegawai> listPegawai = new ArrayList<>();

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, idTim);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                listPegawai.add(setPegawai(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPegawai;
    }
    
    /**
     * ada perubahan algoritma terkait rule 1 orang punya beberapa tim
     * 
     * @param idTim
     * @return 
     */
    @Override
    public List<Pegawai> getPegawaiByTim(String idTim) {
        List<Pegawai> listPegawai = getAllPegawai();
        List<Pegawai> listPegawaiFiltered = new ArrayList<>();

        for (Pegawai p : listPegawai) {
            String[] idTims;
            String[] jabatans;
            if (p.getIdTim().contains("<p>")) {
                idTims = p.getIdTim().split("<p>");
                jabatans = p.getJabatanTim().split("<p>");
            }
            else {
                idTims = new String[]{p.getIdTim()};
                jabatans = new String[]{p.getJabatanTim()};
            }
            
            for (int i=0; i<idTims.length;i++) {
                if (idTims[i].equals(idTim)) {
                    p.setJabatanTim(jabatans[i]);
                    listPegawaiFiltered.add(p);
                    break;
                    
                }
                
            }
            
        }
        
        return listPegawaiFiltered;
    }

    @Override
    public void createPegawai(Pegawai pegawai) {
        String sql = "INSERT INTO pegawai VALUES(?,?,?,?,?,?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, "");
            pstm.setString(2, pegawai.getNipPegawai());
            pstm.setString(3, pegawai.getNamaPegawai());
            pstm.setString(4, pegawai.getGolongan());
            pstm.setString(5, pegawai.getJabatanTim());
            pstm.setString(6, pegawai.getPangkat());
            pstm.setString(7, pegawai.getJabatanDinas());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
    public void setPegawaiTimBack(String nipPegawai, String idTim, String jabatan) {
        String sql = "UPDATE pegawai SET id_tim=?, jabatan_tim=? WHERE nip_pegawai=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, idTim);
            pstm.setString(2, jabatan);
            pstm.setString(3, nipPegawai);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ada perubahan algoritma terkait rule 1 orang punya beberapa tim
     * 
     * @param nipPegawai
     * @param idTim
     * @param jabatan 
     */
    @Override
    public void setPegawaiTim(String nipPegawai, String idTim, String jabatan) {
        Pegawai pegawai = getPegawai(nipPegawai);
        String timCol = "";
        String jabatanTimCol = "";
        
        if (pegawai.getIdTim().equals("")) {
            timCol = idTim;
            jabatanTimCol = jabatan;
        }else {
            //jika tim lebih dari satu
            if (pegawai.getIdTim().contains("<p>")) {
                String[] idTims = pegawai.getIdTim().split("<p>");
                String[] jabatans = pegawai.getJabatanTim().split("<p>");
                boolean exist = false;
                for (int i=0; i<idTims.length;i++) {
                    if (idTims[i].equals(idTim)) {
                        exist = true;
                        jabatans[i] = jabatan;
                        
                        break;
                    }
                }
                
                //berarti tambah tim dan jabatan untuk pegawai selected
                if (!exist) {
                    timCol = pegawai.getIdTim()+"<p>"+idTim;
                    jabatanTimCol = pegawai.getJabatanTim()+"<p>"+jabatan;
                }
                //update jabatan
                else {
                    timCol = pegawai.getIdTim();
                    jabatanTimCol = jabatans[0];
                    for (int i = 1; i<jabatans.length; i++) {
                        jabatanTimCol += "<p>"+jabatans[i];
                    }
                }
                
            }
            //jika tim hanya satu
            else {
                //jika hanya mengupdate jabatan
                System.out.println("case 2");
                if (pegawai.getIdTim().equals(idTim)) {
                    System.out.println("masuk sini : "+pegawai.getIdTim()+":"+idTim);
                    timCol = idTim;
                    jabatanTimCol = jabatan;
                }
                //berarti tambah tim dan jabatan untuk pegawai selected
                else {
                    timCol = pegawai.getIdTim()+"<p>"+idTim;
                    jabatanTimCol = pegawai.getJabatanTim()+"<p>"+jabatan;
                }
            }
        }
                
        String sql = "UPDATE pegawai SET id_tim=?, jabatan_tim=? WHERE nip_pegawai=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, timCol);
            pstm.setString(2, jabatanTimCol);
            pstm.setString(3, nipPegawai);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTim(Tim tim) {
        String sql = "INSERT INTO tim VALUES(?,?)";
        System.out.println("id "+tim.getIdTim()+"; nama "+tim.getNamaTim());
        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, tim.getIdTim());
            pstm.setString(2, tim.getNamaTim());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tim> getAllTim() {
        String sql = "SELECT * FROM tim";
        List<Tim> tims = new ArrayList<>();

        try(Connection conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)) {
            while(rs.next()){
                tims.add(new Tim(rs.getString("id_tim"), rs.getString("nama_tim")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tims;
    }

    @Override
    public void updatePegawai(Pegawai pegawai) {
        String sql = "UPDATE pegawai SET " +
                "nama_pegawai=?, golongan=?, pangkat=?, " +
                "jabatan_dinas=? WHERE nip_pegawai=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, pegawai.getNamaPegawai());
            pstm.setString(2, pegawai.getGolongan());
            pstm.setString(3, pegawai.getPangkat());
            pstm.setString(4, pegawai.getJabatanDinas());
            pstm.setString(5, pegawai.getNipPegawai());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTim(Tim tim) {
        String sql = "UPDATE tim SET nama_tim=? WHERE id_tim=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, tim.getNamaTim());
            pstm.setString(2, tim.getIdTim());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePegawai(String nip) {
        String sql = "DELETE FROM pegawai WHERE nip_pegawai=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nip);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTim(String id) {
        String sql = "DELETE FROM tim WHERE id_tim=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, id);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Pegawai setPegawai(ResultSet rs) throws SQLException {
        return new Pegawai(rs.getString("id_tim"),
                                        rs.getString("nip_pegawai"),
                                        rs.getString("nama_pegawai"),
                                        rs.getString("golongan"),
                                        rs.getString("pangkat"),
                                        rs.getString("jabatan_tim"),
                                        rs.getString("jabatan_dinas"));
    }

    @Override
    public void deletePegawaiFromTim(String nipPegawai, String idTim) {
        Pegawai pegawai = getPegawai(nipPegawai);
        String timCol = "";
        String jabatanTimCol = "";
        //jika tim lebih dari satu
        if (pegawai.getIdTim().contains("<p>")) {
            String[] idTims = pegawai.getIdTim().split("<p>");
            String[] jabatans = pegawai.getJabatanTim().split("<p>");
            for (int i=0; i<idTims.length;i++) {
                System.out.println("in dao test "+idTims[i]);
                if (idTims[i].equals(idTim)) {
                    idTims[i] = "";
                    jabatans[i] = "";

                    break;
                }
            }
            
            List<String> tempList = new ArrayList<>();
            List<String> tempJabList = new ArrayList<>();
            System.out.println("idTims size dao "+idTims.length);
            for (int i = 0; i<idTims.length; i++) {
                System.out.println("in dao "+idTims[i]);
                if (!idTims[i].equals("")) {
                    tempList.add(idTims[i]);
                    tempJabList.add(jabatans[i]);
                }
            }
            System.out.println("temp size dao "+tempList.size());
            timCol = tempList.get(0);
            jabatanTimCol = tempJabList.get(0);
            if (tempList.size() > 1) {
                for (int i=1; i < tempList.size(); i++) {
                    timCol += "<p>"+tempList.get(i);
                    jabatanTimCol += "<p>"+tempJabList.get(i);
                }
            }

        }
        
        String sql = "UPDATE pegawai SET id_tim=?, jabatan_tim=? WHERE nip_pegawai=?";
        
        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, timCol);
            pstm.setString(2, jabatanTimCol);
            pstm.setString(3, nipPegawai);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
