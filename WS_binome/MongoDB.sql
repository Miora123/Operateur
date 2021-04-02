cd c:/
cd MongoDB
cd bin

mongo

show dbs

use telephone

db.createUser({ 
user:"Telephonie",
 pwd:"123456",
 roles: ["readWrite" , "dbAdmin"] 
});



function getNextSequenceDepot(sequenceName){
   var sequenceDocument = db.depot.findAndModify({
      query:{id: sequenceName },
      update: {$inc:{sequence_value:1}},
      new:true
   });
   return sequenceDocument.sequence_value;
}


function getNextSequenceAchatoffre(sequenceName){
   var sequenceDocument = db.achatoffre.findAndModify({
      query:{id: sequenceName },
      update: {$inc:{sequence_value:1}},
      new:true
   });
   return sequenceDocument.sequence_value;
}


function getNextSequenceAppel(sequenceName){
   var sequenceDocument = db.appel.findAndModify({
      query:{id: sequenceName },
      update: {$inc:{sequence_value:1}},
      new:true
   });
   return sequenceDocument.sequence_value;
}


function getNextSequenceAchatcredit(sequenceName){
   var sequenceDocument = db.achatcredit.findAndModify({
      query:{id: sequenceName },
      update: {$inc:{sequence_value:1}},
      new:true
   });
   return sequenceDocument.sequence_value;
}

db.createCollection('achatcredit');
db.createCollection('achatoffre');
db.createCollection('depot'); 
db.createCollection('appel'); 


db.achatoffre.insert({
   "id":getNextSequenceAchatoffre("productid"),
   "idclient":"1",
   "idoffre":"1",
   "dateoffre":"2021-03-01 00:00:00.0",
})


db.appel.insert({
   "id":getNextSequenceAppel("productid"),
   "temps ":"00:03:00",
   "idClient1":"1",
   "idClient2":"2",
})


db.depot.insert({
   "id":1,
   "valeur ":"2000",
   "frais":"1",
   "idClient":"2",
   "datedepot":"2021-03-01 ",
})


db.achatcredit.insert({
   "id":getNextSequenceAchatcredit("productid"),
   "idclient":"cl1",
   "prix":"500",
   "dateachatcredit":"2021-03-01 00:00:00.0",
})
 
db.appel.remove();
db.depot.remove();
db.achatcredit.remove();
db.achatoffre.remove();