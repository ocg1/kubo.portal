package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Access;

import org.apache.log4j.Logger;

public abstract class DAOBitacoraAccess 
{
	protected Logger log = Logger.getLogger(getClass());
	
	protected EntityManager em = null;
	
	protected static final int CIERRE = 6;

    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
	public final Access loadSelectedAccess(int id) 
	{
		return em.find(Access.class,id);
	}
	
	protected String getMenu_imcomplete_QUERY() 
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT ax.*, bx.percentage from ");
		sb.append("(SELECT ");
		sb.append("s.company_id     as company_id, ");
		sb.append("s.name           as name, ");
		sb.append("s.menu_order     as menu_order, ");
		sb.append("s.is_obligatory  as is_obligatory, ");
		sb.append("s.screen_id      as screen_id, ");
		sb.append("s.resource_name  as resource_name, ");
		sb.append("MAX(a.access_id) as access_id, ");
		sb.append("a.prospectus_id ");
		sb.append("FROM      pr_screen as s");
		sb.append("LEFT JOIN pr_access as a");
		sb.append("ON ( s.screen_id     = a.screen_id ");
		sb.append("and  a.company_id    = s.company_id ");
		sb.append("and  a.company_id    = ? ");
		sb.append("and  a.prospectus_id = ? ) ");
		sb.append("WHERE s.menu_order > 0");
		sb.append("and   s.area = ?");
		sb.append("and   s.screen_id <> ").append(CIERRE);
		sb.append("GROUP BY ");
		sb.append("s.company_id,");
		sb.append("s.name,");
		sb.append("s.menu_order,");
		sb.append("s.screen_id,");
		sb.append("s.resource_name,");
		sb.append("a.prospectus_id ");
		sb.append("ORDER BY s.menu_order) as ax");
		sb.append("LEFT JOIN pr_access    as bx");
		sb.append("ON ( ax.access_id = bx.access_id )");
		sb.append("and  bx.percentage < 100 ");
		
		return sb.toString();
	}
}
