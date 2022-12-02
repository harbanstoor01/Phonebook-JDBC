package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.contact;

public class contactDAO {

	private Connection con;

	public contactDAO(Connection con) {
		super();
		this.con = con;
	}

	public boolean saveContact(contact c) {
		boolean f = false;
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into contact(name, phone) values(?,?)");
			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getPhone());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public boolean editContact(contact c) {
		boolean f = false;
		try {
			PreparedStatement pstmt = con.prepareStatement("update contact set phone = ? where id = ?");
			pstmt.setString(1, c.getPhone());
			pstmt.setInt(2, c.getId());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public boolean deleteContact(int id) {
		boolean f = false;
		try {
			PreparedStatement pstmt = con.prepareStatement("delete from contact where id = ?");
			pstmt.setInt(1, id);

			int i = pstmt.executeUpdate();
			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public List<contact> getAllContact() {
		List<contact> list = new ArrayList<contact>();
		contact obj = null;
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from contact");
			ResultSet set = pstmt.executeQuery();

			while (set.next()) {
				obj = new contact();
				obj.setId(set.getInt(1));
				obj.setName(set.getString(2));
				obj.setPhone(set.getString(3));
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
