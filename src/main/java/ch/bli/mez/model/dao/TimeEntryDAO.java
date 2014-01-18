package ch.bli.mez.model.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.SessionManager;
import ch.bli.mez.model.TimeEntry;
import ch.bli.mez.util.Keyword;
import ch.bli.mez.util.Parser;

@SuppressWarnings("unchecked")
public class TimeEntryDAO implements Searchable {

  public TimeEntryDAO() {
  }

  public List<TimeEntry> findAll(Employee employee) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<TimeEntry> timeEntries = session.createQuery(
        "FROM TimeEntry WHERE employee_id = " + employee.getId() + " order by date").list();
    tx.commit();
    return timeEntries;
  }

  public void addTimeEntry(TimeEntry timeEntry) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    try {
      session.save(timeEntry);
      tx.commit();
    } catch (IllegalArgumentException ex) {
      tx.rollback();
      throw ex;
    }
  }

  public TimeEntry getTimeEntry(Integer id) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    TimeEntry timeEntry = (TimeEntry) session.load(TimeEntry.class, id);
    tx.commit();
    return timeEntry;
  }

  public void updateTimeEntry(TimeEntry timeEntry) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    try {
      session.update(timeEntry);
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
    }
  }

  public void deleteTimeEntry(Integer id) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    TimeEntry timeEntry = (TimeEntry) session.load(TimeEntry.class, id);
    if (null != timeEntry) {
      session.delete(timeEntry);
    }
    tx.commit();
  }

  public List<TimeEntry> findByKeywords(String url) {
    Criteria criteria = createCriteria(Keyword.getKeywords(url));
    criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
    return criteria.list();
  }

  private Criteria createCriteria(Map<String, String> keywords) {
    Session session = SessionManager.getSessionManager().getSession();
    Criteria criteria = session.createCriteria(TimeEntry.class);
    Calendar cal = null;
    try {
      cal = Parser.parseDateStringToCalendar(keywords.get("date"));
    } catch (Exception e) {
    }
    if (cal != null) {
      Criterion date = Restrictions.eq("date", cal);
      criteria.add(date);
    }

    EmployeeDAO employeeDAO = new EmployeeDAO();
    Employee employee = employeeDAO.getEmployee(Integer.parseInt(keywords.get("employeeID")));
    if (employee != null) {
      criteria.add(Restrictions.eq("employee", employee));
    }

    MissionDAO missionDAO = new MissionDAO();
    Mission mission = missionDAO.findByMissionName(keywords.get("missionName"));
    if (mission != null) {
      criteria.add(Restrictions.eq("mission", mission));
    }

    PositionDAO positionDAO = new PositionDAO();
    Position position = positionDAO.findByCode(keywords.get("positionCode"));
    if (position != null) {
      criteria.add(Restrictions.eq("position", position));
    }

    if (!keywords.get("worktime").equals("")) {
      criteria.add(Restrictions.eq("worktime", Parser.parseMinuteStringToInteger((keywords.get("worktime")))));
    }
    return criteria;
  }
  
  /**
   * 
   * @param employee
   *          concerned employee
   * @return newest Week in TimeEntries
   */
  public Calendar getNewestDateOfWork(Employee employee) {
    if (employee == null) {
      return null;
    }
    Session session = SessionManager.getSessionManager().getSession();
    List<TimeEntry> timeEntries = session.createCriteria(TimeEntry.class).add(Restrictions.eq("employee", employee))
        .addOrder(Order.desc("date")).list();
    if (timeEntries.size() > 0) {
      return timeEntries.get(0).getDate();
    }
    return null;
  }

  public Integer getWeekSummaryAmount(Employee employee, Calendar weekBegin) {
    if (weekBegin == null || employee == null) {
      return 0;
    }
    Calendar weekEnd = (Calendar) weekBegin.clone();
    weekEnd.add(Calendar.WEEK_OF_YEAR, 1);
    Session session = SessionManager.getSessionManager().getSession();
    Long weekSum = (Long) session.createCriteria(TimeEntry.class).setProjection(Projections.sum("worktime"))
        .add(Restrictions.eq("employee", employee)).add(Restrictions.ge("date", weekBegin))
        .add(Restrictions.lt("date", weekEnd)).uniqueResult();
    if (weekSum != null) {
      return weekSum.intValue();
    }
    return 0;
  }

  public List<TimeEntry> getEntriesForReport(Mission mission, Position position, Calendar endDate, Calendar dateStart) {
    Session session = SessionManager.getSessionManager().getSession();
    Criteria criteria = session.createCriteria(TimeEntry.class);
    if (dateStart != null){
      criteria.add(Restrictions.ge("date", dateStart));
    }
    criteria.add(Restrictions.le("date", endDate));
    criteria.add(Restrictions.eq("mission", mission));
    criteria.add(Restrictions.eq("position", position));
    criteria.add(Restrictions.gt("worktime", 0));
    criteria.add(Restrictions.isNotNull("worktime"));
    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    return criteria.list();
  }
  
  public List<TimeEntry> getEntriesForReport(List<Mission> missions, Position position, Calendar endDate, Calendar dateStart) {
    Session session = SessionManager.getSessionManager().getSession();
    Criteria criteria = session.createCriteria(TimeEntry.class);
    if (dateStart != null){
      criteria.add(Restrictions.ge("date", dateStart));
    }
    criteria.add(Restrictions.le("date", endDate));
    criteria.add(Restrictions.in("mission", missions));
    criteria.add(Restrictions.eq("position", position));
    criteria.add(Restrictions.gt("worktime", 0));
    criteria.add(Restrictions.isNotNull("worktime"));
    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    return criteria.list();
  }
  
  public List<TimeEntry> getEntriesForReport(Mission mission, Position position, Calendar endDate, Calendar dateStart, Employee employee) {
    Session session = SessionManager.getSessionManager().getSession();
    Criteria criteria = session.createCriteria(TimeEntry.class);
    if (dateStart != null){
      criteria.add(Restrictions.ge("date", dateStart));
    }
    criteria.add(Restrictions.le("date", endDate));
    criteria.add(Restrictions.eq("mission", mission));
    criteria.add(Restrictions.eq("position", position));
    criteria.add(Restrictions.gt("worktime", 0));
    criteria.add(Restrictions.isNotNull("worktime"));
    criteria.add(Restrictions.eq("employee", employee));
    criteria.createAlias("employee", "employee");
    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    criteria.addOrder(Order.asc("employee.lastName"));
    return criteria.list();
  }
  
  public List<TimeEntry> getEntriesForReport(List<Mission> missions, Position position, Calendar endDate, Calendar dateStart, Employee employee) {
    Session session = SessionManager.getSessionManager().getSession();
    Criteria criteria = session.createCriteria(TimeEntry.class);
    if (dateStart != null){
      criteria.add(Restrictions.ge("date", dateStart));
    }
    criteria.add(Restrictions.le("date", endDate));
    criteria.add(Restrictions.in("mission", missions));
    criteria.add(Restrictions.eq("position", position));
    criteria.add(Restrictions.gt("worktime", 0));
    criteria.add(Restrictions.isNotNull("worktime"));
    criteria.add(Restrictions.eq("employee", employee));
    criteria.createAlias("employee", "employee");
    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    criteria.addOrder(Order.asc("employee.lastName"));
    return criteria.list();
  }
  
  public Integer getWorktimeForReport(Employee employee, Calendar endDate, Calendar startDate, Mission mission, Position position) {
    if (startDate == null || employee == null || endDate == null) {
      return 0;
    }
    Session session = SessionManager.getSessionManager().getSession();
        Criteria criteria = session.createCriteria(TimeEntry.class).setProjection(Projections.sum("worktime"))
        .add(Restrictions.eq("employee", employee)).add(Restrictions.ge("date", startDate))
        .add(Restrictions.le("date", endDate));
        if (mission != null){
          criteria.add(Restrictions.eq("mission", mission));
        }
        if (position != null){
          criteria.add(Restrictions.eq("position", position));
        }
        Long weekSum = (Long) criteria.uniqueResult();
    if (weekSum != null) {
      return weekSum.intValue();
    }
    return 0;
  }
  
  public List<Mission> getMissionsForReport(Employee employee, Calendar endDate, Calendar startDate){
    if (startDate == null || employee == null || endDate == null) {
      return null;
    }
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Mission> missions = session.createQuery(
        "select DISTINCT mission FROM TimeEntry WHERE employee_id = " + employee.getId() + "AND date>=" + startDate.getTimeInMillis()
            + " AND date<=" + endDate.getTimeInMillis() + " AND worktime > 0 ORDER BY isOrgan DESC, missionName ASC").list();
    tx.commit();
    return missions;
  }
  
  public List<Position> getPositionsForReport(Employee employee, Calendar endDate, Calendar startDate, Mission mission){
    if (startDate == null || employee == null || endDate == null || mission == null) {
      return null;
    }
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Position> positions = session.createQuery(
        "select DISTINCT position FROM TimeEntry WHERE employee_id = " + employee.getId() + " AND mission_mission_id=" + mission.getId() + " AND date>=" + startDate.getTimeInMillis()
            + " AND date<=" + endDate.getTimeInMillis() + " AND worktime > 0 ORDER BY code ASC").list();
    tx.commit();
    return positions;
  }
  
  
  
  
  
  
  
  
  
  
  
  
}
