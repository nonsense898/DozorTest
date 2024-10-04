package com.non.dozortest.util.testsuites

import com.non.dozortest.api.ApiIsolationTests
import com.non.dozortest.database.RoomDatabaseTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    ApiIsolationTests::class,
    RoomDatabaseTest::class
)
class MainTestSuite