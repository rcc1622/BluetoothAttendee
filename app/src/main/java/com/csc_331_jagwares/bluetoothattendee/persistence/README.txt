
AttendeeDatasource
------------------

The main entry point for the Persistence package. Each application
must have exactly one instance of Attendee Datasource. Its get* methods
return model objects, which represent information read from and written to
the underlying datastore.

The application must first instantiate an AttendeeDatasource, passing
a context object to the constructor.

It must call open() BEFORE calling any other methods.

Before the application exits, it must call close() on the datasource to
ensure all pending writes are completed.

Models
------

A Class object represents a particular section of a particular course.
Its getStudents() method returns all students currently enrolled in it.

A Student object represents an individual student. Its getClasses()
method returns all classes the student is enrolled in.