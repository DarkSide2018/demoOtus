package arcadedb

import com.arcadedb.remote.RemoteDatabase


fun main() {
    val db = RemoteDatabase("localhost",2480,"test_arcade","root","playwithdata")
    db.begin()
    val schema="""
            create vertex type Person if not exists;
            create property Person.name if not exists string;
            create property Person.age if not exists string;
            create vertex type Business if not exists;
            create property Business.legal_name if not exists string;
            create property Business.country if not exists string;
            create edge type worksIn if not exists;
    """.trimIndent()
        db.command("sqlscript",schema)
        db.command("sql","insert into Person(name,age) values(?,?)","Petr","24")
        db.command("sql","insert into Business(legal_name,country) values(?,?)","Arcade","RUS")
    db.commit()
    db.begin()
    db.command("sql", "CREATE EDGE worksIn FROM " +
            "(SELECT FROM Business WHERE legal_name = 'Arcade') TO " +
            "            (SELECT FROM Person WHERE name = 'Petr');")
    db.commit()
    db.close()

}