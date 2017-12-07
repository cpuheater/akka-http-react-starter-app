package com.cpuheater.starter.persistence

import com.cpuheater.starter.model.{NewUser, User}
import scalikejdbc.{AutoSession, WrappedResultSet, _}
import scala.concurrent.{ExecutionContext, Future}

object UserDao {

  def create(user: NewUser)(implicit ec: ExecutionContext, session: AutoSession) : Future[User] = {
    val id = sql"""insert into USER
         (email, password) values (${user.email}, ${user.password})""".updateAndReturnGeneratedKey.apply()
    Future.successful(User(id= id, email = user.email, password = user.password))
  }


  def get(id: Long)(implicit ec: ExecutionContext, session: AutoSession) : Future[Option[User]] = {
    val user = sql"select * from USER where id = ${id}".map(rs => UserDB(rs)).first().apply()
    Future.successful(user)
  }

  def getByEmailAndPassword(email: String, password: String)(implicit ec: ExecutionContext, session: AutoSession) : Future[Option[User]] = {
    val user = sql"select * from USER where email = ${email} and password = ${password}".map(rs => UserDB(rs)).first().apply()
    Future.successful(user)
  }


  def getAll()(implicit ec: ExecutionContext, session: AutoSession) : Future[List[User]] = {
    val list = sql"select * from USER".map(rs => UserDB(rs)).list.apply()
    Future.successful(list)
  }

  private object UserDB extends SQLSyntaxSupport[User] {
    override val tableName = "USER"
    def apply(rs: WrappedResultSet): User =
      User(id = rs.long("id"), email = rs.string("email"), password = rs.string("password"))
  }

}
