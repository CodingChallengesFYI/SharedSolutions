for i in {1..20}; do dd if=/dev/urandom bs=100 count=1 of=file$i; done
