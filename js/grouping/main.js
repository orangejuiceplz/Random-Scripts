// check usage of
// _map
// _mapValues
// _values
// _flatten
// _redduce
// _flatMap

const _ = require('lodash');

// extract group name from the field data
function transformField(rawFieldData) {
    return {
        groupName: rawFieldData.group,
        fieldData: _.omit(rawFieldData, ['group'])
    };
}

// basic, advanced
function processCategory(categoryFields) {
    return _.reduce(categoryFields, (result, rawData, fieldName) => {
        
        const { groupName, fieldData } = transformField(rawData);

        if (!result[groupName]) {
            result[groupName] = {};
        }

        result[groupName][fieldName] = fieldData;

        return result;
    }, {});
}

// tries to con vert metadata to exploded
// raw: category -> field -> {type, maxLen, grou}
// exploded: category -> group -> field -> {type, maxLen}
function convertToExploded(metadata) {
    // a b or c
    return _.mapValues(metadata, (categoryData) => {
        return processCategory(categoryData);
    });
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
