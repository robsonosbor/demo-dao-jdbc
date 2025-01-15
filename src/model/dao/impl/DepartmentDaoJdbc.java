package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJdbc implements DepartmentDao{

	private Connection conn;
	
	public DepartmentDaoJdbc(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)");
			st.setString(1, obj.getName());
			int rows = st.executeUpdate();
			
			if (rows > 0)
				System.out.printf("Insert done! %d lines affected.", rows);
			else
				System.out.printf("Insert Error!", rows);
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE department "
					+ "SET Name = ?"
					+ "WHERE Id = ?");
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			int rows = st.executeUpdate();
			
			if (rows > 0)
				System.out.printf("Update done! %d lines affected.", rows);
			else
				System.out.printf("Update Error!", rows);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;		
		
		try {
			st = conn.prepareStatement("DELETE FROM department WHERE Id=?");
			st.setInt(1, id);
			int result = st.executeUpdate();
			
			if (result > 0)
				System.out.printf("Delete done! %d lines affected.", result);
			else
				System.out.printf("Delete Error!", result);		
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * "
					+ "FROM department "
					+ "WHERE Id = ?"
					);
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department obj = instatiateDepartment(rs);
				return obj;
			}
			
			return null;
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Department> deps = new ArrayList<>();
		try {
			st = conn.prepareStatement("SELECT * FROM department");
			rs = st.executeQuery();
			
			while(rs.next()) {
				Department dep = instatiateDepartment(rs);
				deps.add(dep);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		return deps;
	}
	
	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		
		return dep;
	}

}
