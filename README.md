# dynamicmondrian

A Mondrian implementation with a REST webservice that allows for dynamic Schemas based on queries.

Problem: You do not want to read your whole BI database everytime you need to update the database image for a query. 
This is specially onerous for cases where mondrian needs to display updated information on demand.

Solution: Alter the Schema dynamically everytime a new query is made. This way, if an user is interested on data for the last day, 
mondrian will read only said data on the database.



#How it works

Before querying, send a post request to /filter with an ID, a report name, and the parameters.
The example provided uses two parameters, startDate and finalDate, so the post request body Would look like this:
{
{
	"dataInicial":"18/02/2019",
	"dataFinal":"18/02/2019",
	"user":"makotao",
	"reports":"sales"
}
}
