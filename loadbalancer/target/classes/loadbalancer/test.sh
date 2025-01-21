for i in {1..100000}
do
  curl -X GET http://localhost:1010 --data "Request $i"
  echo "Request $i sent"
done