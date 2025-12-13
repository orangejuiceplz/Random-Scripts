// check usage of
// _map
// _mapValues
// _values
// _redduce
// _flatMap

// extract group name from the field data
function transformField(rawFieldData) {
    const { group, ...rest } = rawFieldData;
    return {
        groupName: group,
        fieldData: rest
    };
}

/**
 * organizes fields into groups for one category 
 * 
 * 1. loop through every field in the category
 * 2. separate the group name from the data (calling transformField())
 * 3. create the group object if it doesn't exist
 * 4. assign the field to that group
*/ 
function processCategory(categoryFields) {
    const grouped = {};

    for (const [fieldName, rawData] of Object.entries(categoryFields)) {

        const { groupName, fieldData } = transformField(rawData);

        if (!grouped[groupName]) {
            grouped[groupName] = {};
        }

        grouped[groupName][fieldName] = fieldData;
    }

    return grouped;
}

/**
 * 1. loop through a b and c (category)
 * 2. process the fields for that category using the above fucntions
 */
function convertToExploded(metadata) {
    const result = {};

    for (const [categoryName, categoryData] of Object.entries(metadata)) {
        result[categoryName] = processCategory(categoryData);
    }

    return result;
}
const metadata = {
    A: {
        FIELD_1: {
            type: "BOOLEAN",
            maxLen: 0,
            group: "BASIC"
        },
        FIELD_2: {
            type: "STRING",
            maxLen: 50,
            group: "BASIC"
        },
        FIELD_3: {
            type: "INTEGER",
            maxLen: 0,
            group: "ADVANCED"
        }
    },
    B: {
        FIELD_4: {
            type: "DOUBLE",
            maxLen: 0,
            group: "ADVANCED"
        }
    },
    C: {
        FIELD_5: {
            type: "STRING",
            maxLen: 50,
            group: "BASIC"
        }
    }
};

const exploded = convertToExploded(metadata);

/*
const newExploded = map(metaData, function(item) {
    return { }
});
*/
// console.log(JSON.stringify(exploded, null, 2));
