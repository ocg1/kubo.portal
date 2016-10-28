package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesPK;
import mx.com.kubo.repositories.ExpensesDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ExpensesDaoImp implements ExpensesDao {
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
	@Override
	public Expenses getExpensesbyID(ExpensesPK expensesPk) {
		return em.find(Expenses.class, expensesPk);
	}

	@Transactional
	@Override
	public boolean addExpenses(Expenses newExpenses, int prospectusID,int companyID) {
		Integer idExpenses=0;
		idExpenses=(Integer) em.createNamedQuery("queryAddExpenses")
				.setParameter(1, prospectusID)
				.setParameter(2, companyID)
				.getSingleResult();
		if(idExpenses==null){			
			idExpenses=1;
		}
		else{
			idExpenses++;
			}
		log.info("Maximo de egresos= "+idExpenses);
		newExpenses.getExpensesPk().setExpense_id(idExpenses);
		try {
			em.persist(newExpenses);			
			return true;
			} catch (Exception e) {
				return false;
			}
	}
	
	@Transactional
	@Override
	public boolean updateExpenses(Expenses expenses) {
		log.info("updateExpenses.ExpensesDaoImp");
		try{
			em.merge(expenses);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Expenses getExpensesByTypeExpensesID(int prospectusID,int companyID, int typeExpensesID) {
		String query="select Max(expense_id) from gn_expense  where prospectus_id=? and company_id=? and expense_type_id=? ";
		Integer id=0;
		id=(Integer)em.createNativeQuery(query)
				.setParameter(1,prospectusID)
				.setParameter(2, companyID)
				.setParameter(3, typeExpensesID)
				.getSingleResult();
		log.info("Expenses id es= "+id);
		if(id==null){
			return null;
		}
		else{
			ExpensesPK expensesPk=new ExpensesPK(id,prospectusID,companyID);	
			return em.find(Expenses.class,expensesPk);
		}
	}

	@Override
	public List<Expenses> getListExpensesByProspect(int prospectusID,int companyID) {
		String query="from Expenses e where e.expensesPk.prospectus_id=? and e.expensesPk.company_id=?";
		List<Expenses> expensesList=em.createQuery(query,Expenses.class)
				.setParameter(1, prospectusID)
				.setParameter(2, companyID)
				.getResultList();
		return expensesList;
	}

}
