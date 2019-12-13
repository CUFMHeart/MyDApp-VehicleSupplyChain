pragma solidity ^0.4.23;

// import './Ownable.sol';

// contract VehicleSupplyChain is Ownable {
contract VehicleSupplyChain {
	enum CompanyType {
		Bank,
		CarCompany,
		TireCompany,
		HubCompany
	}

	struct Company {
		uint256 company_id;
		bytes32 company_name;
        address company_address;	//  address->uint160
		uint256 company_asset;
		uint256 company_receipts_in;
		uint256 company_receipts_out;
		CompanyType company_type;
		bool valid;	//	company is alive?
		mapping (address => Receipt) from_receipts;
		// mapping (address => Receipt) to_receipts;
	}

	struct Receipt {
		// bytes32 hash;
		uint256 amount;
		address from;
		address to;
		bool valid;	//	receipt is alive?
	}

	uint256 private company_id_count = 1;
	mapping(address => Company) public addressToCompany;
	mapping(uint256 => Company) public idToCompany;
	// mapping(bytes32 => Receipt) public hashToReceipt;

	// function VehicleSupplyChain() public {
	// 	// owner = this_address;
	// }

	Company bank;

	constructor () {
		// registerCompany(bank, 0);
        address bank_address = 0xED23E54C2c913a6f2Baf0142da49cD535cb03D7a;
		bank.company_id = 1;
		bank.company_name = "bank";
        bank.company_address = bank_address;
		bank.company_asset = 500000;
		bank.company_receipts_in = 0;
		bank.company_receipts_out = 0;
		bank.company_type = CompanyType(0);
		bank.valid = true;
		idToCompany[1] = bank;
		addressToCompany[bank_address] = bank;
	}

	// function registerCompany(bytes32 company_name, uint8 company_type) public onlyOwner {
	function registerCompany(address this_address, bytes32 company_name, uint8 company_type) {
		company_id_count = company_id_count + 1;
		idToCompany[company_id_count] = Company(company_id_count, company_name, this_address, 50000, 0, 0, CompanyType(company_type), true);
		addressToCompany[this_address] = idToCompany[company_id_count];
	}

	function getCompanyAsset(address this_address) public constant returns(uint256) {
		return addressToCompany[this_address].company_asset;
	}

	function getReceiptAmount(address this_address, address to_address) public returns(uint256) {
		return addressToCompany[this_address].from_receipts[to_address].amount;
	}

	function signReceipt(address this_address, address from_temp, uint256 amount) public {
		//	from -> this
		//	this receipt is alive?
		if (addressToCompany[from_temp].from_receipts[this_address].valid == true) {
			addressToCompany[from_temp].from_receipts[this_address].amount += amount;
		} else {
			addressToCompany[from_temp].from_receipts[this_address] = Receipt(amount, from_temp, this_address, true);
		}
		addressToCompany[this_address].company_receipts_in += amount;
		addressToCompany[from_temp].company_receipts_out += amount;
	}

	function transferReceipt(address this_address, address from_temp, address to_temp, uint256 amount) public {
		//	from -> mid/this ->to
		address mid_temp = this_address;
		//	these receipts are alive?
		if (addressToCompany[from_temp].from_receipts[mid_temp].valid == true && addressToCompany[mid_temp].from_receipts[to_temp].valid == true) {
			//	from -a1-> mid -a2-> to
			//	=>	from -(a1-a)-> mid
			//		mid -(a2-a)-> to
			//		from -a-> to
			//	=>	a1>a, a2>a
			if (addressToCompany[from_temp].from_receipts[mid_temp].amount >= amount && addressToCompany[mid_temp].from_receipts[to_temp].amount >= amount) {
				addressToCompany[from_temp].from_receipts[mid_temp].amount -= amount;
				addressToCompany[mid_temp].from_receipts[to_temp].amount -= amount;
				//	amount == 0?
				if (addressToCompany[from_temp].from_receipts[mid_temp].amount == 0) {
					addressToCompany[from_temp].from_receipts[mid_temp].valid = false;
				}
				if (addressToCompany[mid_temp].from_receipts[to_temp].amount == 0) {
					addressToCompany[mid_temp].from_receipts[to_temp].valid = false;
				}
				addressToCompany[mid_temp].company_receipts_in -= amount;
				addressToCompany[mid_temp].company_receipts_out -= amount;
				//	from -> to exist?
				if (addressToCompany[from_temp].from_receipts[to_temp].valid == true) {
					addressToCompany[from_temp].from_receipts[to_temp].amount += amount;
				} else {
					//	to -> from?
					//	Not for the moment, bill cancellation should be implemented in a separate function
					addressToCompany[from_temp].from_receipts[to_temp] = Receipt(amount, from_temp, to_temp, true);
				}
			}
		}
	}

	function financingWithReceipt(address this_address, uint256 amount) public returns(bool) {
		//	Financing failure
		if (addressToCompany[this_address].company_receipts_out < amount ||
			bank.valid == false ||
			bank.company_asset < amount) {
			return false;
		}
		//	bank -> this exist?
		if (bank.from_receipts[this_address].valid == true) {
			bank.from_receipts[this_address].amount += amount;
		} else {
			bank.from_receipts[this_address] = Receipt(amount, bank.company_address, this_address, true);
		}
		addressToCompany[this_address].company_receipts_in += amount;
		bank.company_receipts_out += amount;
		return true;
	}

	function clearReceipt(address this_address, address from_temp) public returns(bool) {
		//	from -> this => this -payback-> from
		//	Payback failure
		if (addressToCompany[from_temp].from_receipts[this_address].valid == false ||
			addressToCompany[this_address].company_asset < addressToCompany[from_temp].from_receipts[this_address].amount) {
			return false;
		}
		uint256 thisAmount = addressToCompany[from_temp].from_receipts[this_address].amount;
		addressToCompany[from_temp].from_receipts[this_address].valid = false;
		addressToCompany[this_address].company_asset -= thisAmount;
		addressToCompany[from_temp].company_asset += thisAmount;		
		addressToCompany[this_address].company_receipts_in -= thisAmount;
		addressToCompany[from_temp].company_receipts_out -= thisAmount;		
		return true;
	}
}