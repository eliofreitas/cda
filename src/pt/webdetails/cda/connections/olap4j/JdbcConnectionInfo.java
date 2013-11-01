package pt.webdetails.cda.connections.olap4j;

import java.util.List;
import java.util.Properties;

import org.dom4j.Element;
import org.pentaho.reporting.libraries.base.util.StringUtils;

/**
 * Todo: Document me!
 * <p/>
 * Date: 16.02.2010
 * Time: 12:51:09
 *
 * @author Thomas Morgner.
 */
public class JdbcConnectionInfo
{

  private String driver;
  private String url;
  private String user;
  private String pass;
  private Properties properties;
  private String roleField;
  private String userField;
  private String passwordField;


  public JdbcConnectionInfo(final Element connection)
  {

    final String driver = (String) connection.selectObject("string(./Driver)");
    final String url = (String) connection.selectObject("string(./Url)");
    final String userName = (String) connection.selectObject("string(./User)");
    final String password = (String) connection.selectObject("string(./Pass)");
    final String roleFormula = (String) connection.selectObject("string(./RoleField)");
    final String userFormula = (String) connection.selectObject("string(./UserField)");
    final String passFormula = (String) connection.selectObject("string(./PassField)");

    if (StringUtils.isEmpty(driver))
    {
      throw new IllegalStateException("A driver is mandatory");
    }
    if (StringUtils.isEmpty(url))
    {
      throw new IllegalStateException("A url is mandatory");
    }

    setDriver(driver);
    setUrl(url);
    if (StringUtils.isEmpty(userName) == false)
    {
      setUser(userName);
    }
    if (StringUtils.isEmpty(password) == false)
    {
      setPass(password);
    }
    if (StringUtils.isEmpty(userFormula) == false)
    {
      setUserField(userFormula);
    }
    if (StringUtils.isEmpty(passFormula) == false)
    {
      setPasswordField(passFormula);
    }
    if (StringUtils.isEmpty(roleFormula) == false)
    {
      setRoleField(roleFormula);
    }

    properties = new Properties();
    final List list = connection.elements("Property");
    for (int i = 0; i < list.size(); i++)
    {
      final Element childElement = (Element) list.get(i);
      final String name = childElement.attributeValue("name");
      final String text = childElement.getText();
      properties.put(name, text);
    }
  }

  public String getRoleField()
  {
    return roleField;
  }

  public void setRoleField(final String roleField)
  {
    this.roleField = roleField;
  }

  public String getUserField()
  {
    return userField;
  }

  public void setUserField(final String userField)
  {
    this.userField = userField;
  }

  public String getPasswordField()
  {
    return passwordField;
  }

  public void setPasswordField(final String passwordField)
  {
    this.passwordField = passwordField;
  }

  public String getDriver()
  {
    return driver;
  }

  public void setDriver(final String driver)
  {
    this.driver = driver;
  }

  public String getUrl()
  {
    return url;
  }

  public void setUrl(final String url)
  {
    this.url = url;
  }

  public String getUser()
  {
    return user;
  }

  public void setUser(final String user)
  {
    this.user = user;
  }

  public String getPass()
  {
    return pass;
  }

  public void setPass(final String pass)
  {
    this.pass = pass;
  }

  public Properties getProperties()
  {
    return this.properties;
  }

  public boolean equals(final Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }

    final JdbcConnectionInfo that = (JdbcConnectionInfo) o;

    if (driver != null ? !driver.equals(that.driver) : that.driver != null)
    {
      return false;
    }
    if (pass != null ? !pass.equals(that.pass) : that.pass != null)
    {
      return false;
    }
    if (url != null ? !url.equals(that.url) : that.url != null)
    {
      return false;
    }
    if (user != null ? !user.equals(that.user) : that.user != null)
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    int result = driver != null ? driver.hashCode() : 0;
    result = 31 * result + (url != null ? url.hashCode() : 0);
    result = 31 * result + (user != null ? user.hashCode() : 0);
    result = 31 * result + (pass != null ? pass.hashCode() : 0);
    return result;
  }
}