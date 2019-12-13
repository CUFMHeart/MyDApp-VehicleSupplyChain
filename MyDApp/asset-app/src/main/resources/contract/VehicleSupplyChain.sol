pragma solidity ^0.4.23;

// import './Ownable.sol';

// contract VehicleSupplyChain is Ownable {
contract VehicleSupplyChain {
	// enum CompanyType {
	// 	Bank,
	// 	CarCompany,
	// 	TireCompany,
	// 	HubCompany
	// }

	struct Company {
		uint256 company_id;
		string company_name;
        address company_address;	//  address->uint160
		uint256 company_asset;
		uint256 company_receipts_in;
		uint256 company_receipts_out;
		// CompanyType company_type;
		bool valid;	//	company is alive?
		mapping (address => Receipt) from_receipts;
		// mapping (address => Receipt) to_receipts;
	}

	struct Receipt {
		// string hash;
		uint256 amount;
		address from;
		address to;
		bool valid;	//	receipt is alive?
	}

	uint256 private company_id_count = 1;
	mapping(address => Company) public addressToCompany;
	mapping(uint256 => Company) public idToCompany;
	mapping(bytes32 => address) public stringToCompanyAddress;

	
	address[4] private keyArr = [
		0xED23E54C2c913a6f2Baf0142da49cD535cb03D7a,
		0x0637e97D8Bc4C51bef305E4426ebC6fbDCB8Ac81,
		0xCF615d9D5cC285cFFb62008bDF7B8A26495F1200,
		0x55bb5204fD3E78B2b8341C5b9Ca8228BD4F9DF0A
	];

	function stringToBytes32(string memory source) returns (bytes32 result) {
    	assembly { result := mload(add(source, 32)) }
  	}

	// function stringToInt(string str) public returns(uint256 result) {
	// 	bytes32 b;
	// 	assembly { b := mload(add(str, 32)) }
	// 	result = uint256(b);
	// }

	function intToString(uint256 x) public returns(string result) {
        bytes memory b = new bytes(32);
        assembly { mstore(add(b, 32), x) }
		result = string(b);
	}

	// mapping(string => Receipt) public hashToReceipt;

	// function VehicleSupplyChain() public {
	// 	// owner = this_address;
	// }

	Company bank;

	constructor () {
		// registerCompany(bank, 0);
        address bank_address = keyArr[0];
		bank.company_id = 1;
		bank.company_name = "bank";
        bank.company_address = bank_address;
		bank.company_asset = 1000000;
		bank.company_receipts_in = 0;
		bank.company_receipts_out = 0;
		// bank.company_type = CompanyType(0);
		bank.valid = true;
		idToCompany[1] = bank;
		addressToCompany[bank_address] = bank;
		stringToCompanyAddress[stringToBytes32("bank")] = keyArr[0];
		stringToCompanyAddress[stringToBytes32("CarCompany")] = keyArr[1];
		stringToCompanyAddress[stringToBytes32("TireCompany")] = keyArr[2];
		stringToCompanyAddress[stringToBytes32("HubCompany")] = keyArr[3];
	}

	// function registerCompany(string company_name, uint256 company_type) public onlyOwner {
	// function registerCompany(string company_name, uint256 company_type) {
	function registerCompany(string company_name) {
		address this_address = stringToCompanyAddress[stringToBytes32(company_name)];
		company_id_count = company_id_count + 1;
		idToCompany[company_id_count] = Company(company_id_count, company_name, this_address, 50000, 0, 0, true);
		addressToCompany[this_address] = idToCompany[company_id_count];
	}

	function getCompanyAsset(string company_name) public returns(string) {
		address this_address = stringToCompanyAddress[stringToBytes32(company_name)];
		return intToString(addressToCompany[this_address].company_asset);
	}

	function getReceiptAmount(string company_name, string to_company_name) public returns(string) {
		address this_address = stringToCompanyAddress[stringToBytes32(company_name)];
		address to_address = stringToCompanyAddress[stringToBytes32(to_company_name)];
		if (addressToCompany[this_address].from_receipts[to_address].valid){
			return intToString(addressToCompany[this_address].from_receipts[to_address].amount);
		}
		return "0";
	}

	function signReceipt(string company_name, string from_temp_name, int amount_string) public {
		uint256 amount = uint256(amount_string);
		address this_address = stringToCompanyAddress[stringToBytes32(company_name)];
		address from_temp = stringToCompanyAddress[stringToBytes32(from_temp_name)];
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

	function transferReceipt(string company_name, string from_temp_name, string to_temp_name, int amount_string) public {
		uint256 amount = uint256(amount_string);
		address this_address = stringToCompanyAddress[stringToBytes32(company_name)];
		address from_temp = stringToCompanyAddress[stringToBytes32(from_temp_name)];
		address to_temp = stringToCompanyAddress[stringToBytes32(to_temp_name)];
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

	function financingWithReceipt(string company_name, int amount_string) public returns(string) {
		uint256 amount = uint256(amount_string);
		address this_address = stringToCompanyAddress[stringToBytes32(company_name)];
		//	Financing failure
		if (addressToCompany[this_address].company_receipts_out < amount ||
			bank.valid == false ||
			bank.company_asset < amount) {
			return "Failure!";
		}
		//	bank -> this exist?
		if (bank.from_receipts[this_address].valid == true) {
			bank.from_receipts[this_address].amount += amount;
		} else {
			bank.from_receipts[this_address] = Receipt(amount, bank.company_address, this_address, true);
		}
		addressToCompany[this_address].company_receipts_in += amount;
		bank.company_receipts_out += amount;
		return "Success!";
	}

	function clearReceipt(string company_name, string from_temp_name) public returns(string) {
		address this_address = stringToCompanyAddress[stringToBytes32(company_name)];
		address from_temp = stringToCompanyAddress[stringToBytes32(from_temp_name)];
		//	from -> this => this -payback-> from
		//	Payback failure
		if (addressToCompany[from_temp].from_receipts[this_address].valid == false ||
			addressToCompany[this_address].company_asset < addressToCompany[from_temp].from_receipts[this_address].amount) {
			return "Failure!";
		}
		uint256 thisAmount = addressToCompany[from_temp].from_receipts[this_address].amount;
		addressToCompany[from_temp].from_receipts[this_address].amount = 0;
		addressToCompany[from_temp].from_receipts[this_address].valid = false;
		addressToCompany[this_address].company_asset -= thisAmount;
		addressToCompany[from_temp].company_asset += thisAmount;		
		addressToCompany[this_address].company_receipts_in -= thisAmount;
		addressToCompany[from_temp].company_receipts_out -= thisAmount;		
		return "Success!";
	}
}